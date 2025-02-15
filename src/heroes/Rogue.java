package heroes;

import abilities.Backstab;
import abilities.Paralysis;
import attack.visitor.FightContext;
import utility.Coordinate;
import terrain.Terrain;

public final class Rogue extends Hero {
    private static final int ROGUE_START_HP = 600;
    private static final int ROGUE_LEVEL_UP_HP = 40;
    public static final float ROGUE_WOODS_BONUS = 0.15f;

    public Rogue(final int id, final Coordinate position, final Terrain terrain) {
        super(id, position, terrain);
        abilities.add(new Backstab());
        abilities.add(new Paralysis());
    }

    @Override
    public int getStartingHp() {
        return ROGUE_START_HP;
    }

    @Override
    public int getHpPerLevel() {
        return ROGUE_LEVEL_UP_HP;
    }

    @Override
    public float getModifier(final FightContext fightContext) {
        return fightContext.acceptFighter(this);
    }

    @Override
    public String getIdentifier() {
        return "R";
    }

    @Override
    public String getHeroName() {
        return "Rogue";
    }
}
