package heroes;

import abilities.Deflect;
import abilities.Drain;
import attack.visitor.FightContext;
import utility.Coordinate;
import terrain.Terrain;

public final class Wizard extends Hero {
    private static final int WIZARD_START_HP = 400;
    private static final int WIZARD_LEVEL_UP_HP = 30;
    public static final float WIZARD_DESERT_BONUS = 0.1f;

    public Wizard(final Coordinate position, final Terrain terrain) {
        super(position, terrain);
        abilities.add(new Drain());
        abilities.add(new Deflect());
    }

    @Override
    public int getStartingHp() {
        return WIZARD_START_HP;
    }

    @Override
    public int getHpPerLevel() {
        return WIZARD_LEVEL_UP_HP;
    }

    @Override
    public float getModifier(final FightContext fightContext) {
        return fightContext.acceptFighter(this);
    }

    @Override
    public String getIdentifier() {
        return "W";
    }

    @Override
    public boolean isImmuneToDeflect() {
        return true;
    }

}
