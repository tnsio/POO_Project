package heroes;

import abilities.Ability;
import attack.visitor.FightContext;
import attack.visitor.Fighter;
import game.action.GameAction;
import game.action.KillAction;
import game.action.LevelUpAction;
import game.observer.Observable;
import game.observer.Observer;
import great.magician.GreatMagician;
import map.GameMap;
import utility.Coordinate;
import terrain.Terrain;

import java.util.ArrayList;

public abstract class Hero implements Fighter, Observable {
    private static final int FIRST_LEVEL_EXP = 250;
    private static final int LEVEL_EXP = 50;
    private static final int BASE_EXP_GAIN = 200;
    public static final int EXP_HANDICAP = 40;
    private final int id;
    private int maxHp;
    private int currentHp;
    private int exp = 0;
    private int level = 0;
    private boolean alive = true;
    private Coordinate position;
    private Terrain terrain;
    protected ArrayList<Ability> abilities = new ArrayList<Ability>();
    private int damageOverTime = 0;
    private int damageOverTimeDuration = 0;
    private boolean incapacitated = false;
    private int incapacitatedDuration = 0;
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public Hero(final int id, final Coordinate position, final Terrain terrain) {
        this.id = id;
        this.position = position;
        this.terrain = terrain;
        this.maxHp = getStartingHp();
        this.currentHp = getStartingHp();
        observers.add(GreatMagician.getInstance());
    }

    public final Coordinate getPosition() {
        return position;
    }

    public final Terrain getTerrain() {
        return terrain;
    }

    public final void setTerrainFrom(final GameMap gameMap) {
        this.terrain = gameMap.getTerrain(position);
    }

    public final int getLevel() {
        return level;
    }

    public final ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public abstract int getStartingHp();
    public abstract int getHpPerLevel();


    public abstract float getModifier(FightContext fightContext);

    public abstract String getHeroName();

    public final boolean isAlive() {
        return alive;
    }

    public abstract String getIdentifier();

    public final int getId() {
        return id;
    }

    public final int getMaxHp() {
        return maxHp;
    }

    public final int getCurrentHp() {
        return currentHp;
    }

    public final float getLandModifier() {
        return getModifier(getTerrain());
    }

    @Override
    public final String toString() {
        if (alive) {
            return getIdentifier() + " "
                    + level + " "
                    + exp + " "
                    + currentHp + " "
                    + position.getVertical() + " "
                    + position.getHorizontal();
        } else {
            return getIdentifier() + " dead";
        }
    }

    public final void applyStatusEffects() {
        if (incapacitatedDuration > 0) {
            incapacitatedDuration -= 1;
        }


        if (damageOverTimeDuration > 0) {
            damageOverTimeDuration -= 1;
            takeDamage(damageOverTime);
        }
    }

    public final void move(final char moveChar) {
        if (!incapacitated && alive) {
            position.move(moveChar);
        }

        if (incapacitatedDuration == 0) {
            incapacitated = false;
        }
    }

    public final void takeDamage(final int damage) {
        currentHp -= damage;
        if (currentHp <= 0) {
            alive = false;
        }
    }

    public final void takeDamageFrom(final int damage, final Hero attacker) {
        takeDamage(damage);
        if (!alive) {
            notifyAllObservers(new KillAction(attacker, this));
        }
    }

    public final void loot(final Hero victim) {
        if (!victim.isAlive()) {
            exp += Math.max(0, BASE_EXP_GAIN - (level - victim.getLevel()) * EXP_HANDICAP);
        }

        levelUp();
    }

    public final void levelUp() {
        int previousLevel = level;
        calculateLevel();

        if (previousLevel < level && alive) {
            maxHp = getStartingHp() + getHpPerLevel() * level;
            currentHp = maxHp;
            //TODO be careful here if a dead hero levels up
            notifyAllObservers(new LevelUpAction(this, previousLevel));
        }
    }

    private void calculateLevel() {
        if (exp < FIRST_LEVEL_EXP) {
            level = 0;
        } else {
            level = ((exp - FIRST_LEVEL_EXP) / LEVEL_EXP) + 1;
        }
    }

    public final boolean isIncapacitated() {
        return incapacitated;
    }

    public final int getAttackDamage(final Hero victim) {
        int damage = 0;
        for (Ability ability: abilities) {
            damage += Math.round(ability.getFinalDamage(this, victim));
        }

        return damage;
    }

    public final int getDeflectableDamage(final Hero victim) {
        int damage = 0;
        for (Ability ability: abilities) {
            damage += Math.round(ability.getDamageOnTerrain(this, victim));
        }

        return damage;
    }

    public final void afflictStatusEffects(final Hero victim) {
        if (victim.isAlive()) {
            for (Ability ability : abilities) {
                ability.afflictStatusEffects(this, victim);
            }
        }
    }

    public final void afterAttack() {
        for (Ability ability: abilities) {
            ability.afterCast();
        }
    }

    public final void takeOverTimeDamage(final int damage, final int duration) {
        damageOverTime = damage;
        damageOverTimeDuration = duration;
    }

    public final void beIncapacitated(final int duration) {
        if (duration > 0) {
            incapacitated = true;
            incapacitatedDuration = duration;
        }
    }

    public final void cure() {
        damageOverTimeDuration = 0;
        damageOverTime = 0;
        incapacitatedDuration = 0;
        incapacitated = false;
    }

    /**
     * Return true if the hero can take damage from deflect.
     * Override in classes that are immune.
     * @return
     */
    public boolean isImmuneToDeflect() {
        return false;
    }

    @Override
    public final void addObserver(final Observer observer) {
        observers.add(observer);
    }

    public final void notifyAllObservers(final GameAction gameAction) {
        for (Observer observer: observers) {
            observer.update(gameAction);
        }
    }
}
