package heroes;

import abilities.Ability;
import attack.visitor.FightContext;
import attack.visitor.Fighter;
import utility.Coordinate;
import terrain.Terrain;

import java.util.ArrayList;

public abstract class Hero implements Fighter {
    private static final int FIRST_LEVEL_EXP = 250;
    private static final int LEVEL_EXP = 50;
    private int maxHp;
    private int currentHp;
    private int exp = 0;
    private int level = 0;
    private boolean alive = true;
    private Coordinate position;
    private Terrain terrain;
    private ArrayList<Ability> abilities = new ArrayList<Ability>();
    private int damageOverTime = 0;
    private int damageOverTimeDuration = 0;
    private boolean incapacitated = false;
    private int incapacitatedDuration = 0;

    public Hero(final Coordinate position, final Terrain terrain) {
        this.position = position;
        this.terrain = terrain;
        this.maxHp = getStartingHp();
        this.currentHp = getStartingHp();
    }

    public final Coordinate getPosition() {
        return position;
    }

    public final Terrain getTerrain() {
        return terrain;
    }

    public final void setTerrain(final Terrain terrain) {
        this.terrain = terrain;
    }

    public abstract int getStartingHp();
    public abstract int getHpPerLevel();

    public abstract float getModifier(FightContext fightContext);

    public final boolean isAlive() {
        return alive;
    }

    public abstract String getIdentifier();

    @Override
    public final String toString() {
        if (alive) {
            return getIdentifier() + " "
                    + level + " "
                    + exp + " "
                    + currentHp + " "
                    + position.getHorizontal() + " "
                    + position.getVertical();
        } else {
            return getIdentifier() + " dead";
        }
    }

    public final void applyStatusEffects() {
        if (incapacitatedDuration > 0) {
            incapacitatedDuration -= 1;
        }

        if (incapacitatedDuration == 0) {
            incapacitated = false;
        }

        if (damageOverTimeDuration > 0) {
            damageOverTimeDuration -= 1;
            takeDamage(damageOverTime);
        }
    }

    public final void takeDamage(final int damage) {
        currentHp -= damage;
        if (currentHp < 0) {
            alive = false;
        }
    }

    public final void gainExp(final int expGained) {
        int previousLevel = level;
        exp += expGained;
        if (exp < FIRST_LEVEL_EXP) {
            level = 0;
        } else {
            level = (exp - FIRST_LEVEL_EXP) / LEVEL_EXP + 1;
        }

        if (previousLevel < level) {
            maxHp = getStartingHp() + getHpPerLevel() * level;
            currentHp = maxHp;
        }
    }

    public final boolean isIncapacitated() {
        return incapacitated;
    }
}
