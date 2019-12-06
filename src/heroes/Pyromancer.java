package heroes;

import attack.visitor.FightContext;
import utility.Coordinate;
import terrain.Terrain;

public final class Pyromancer extends Hero {
    private static final int PYROMANCER_START_HP = 500;
    private static final int PYROMANCER_LEVEL_UP_HP = 50;
    public static final float PYROMANCER_VOLCANIC_BONUS = 0.25f;

    public Pyromancer(final Coordinate position, final Terrain terrain) {
        super(position, terrain);
    }

    @Override
    public int getStartingHp() {
        return PYROMANCER_START_HP;
    }

    @Override
    public int getHpPerLevel() {
        return PYROMANCER_LEVEL_UP_HP;
    }

    @Override
    public float getModifier(final FightContext fightContext) {
        return fightContext.acceptFighter(this);
    }

    @Override
    public String getIdentifier() {
        return "P";
    }
}
