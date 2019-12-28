package heroes;

import abilities.Execute;
import abilities.Slam;
import attack.visitor.FightContext;
import utility.Coordinate;
import terrain.Terrain;

public final class Knight extends Hero {
    private static final int KNIGHT_START_HP = 900;
    private static final int KNIGHT_LEVEL_UP_HP = 80;
    public static final float KNIGHT_LAND_BONUS = 0.15f;

    public Knight(final int id, final Coordinate position, final Terrain terrain) {
        super(id, position, terrain);
        abilities.add(new Slam());
        abilities.add(new Execute());
    }

    @Override
    public int getStartingHp() {
        return KNIGHT_START_HP;
    }

    @Override
    public int getHpPerLevel() {
        return KNIGHT_LEVEL_UP_HP;
    }

    @Override
    public float getModifier(final FightContext fightContext) {
        return fightContext.acceptFighter(this);
    }

    @Override
    public String getIdentifier() {
        return "K";
    }

    @Override
    public String getHeroName() {
        return "Knight";
    }
}
