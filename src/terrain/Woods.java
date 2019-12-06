package terrain;

import heroes.Rogue;

public final class Woods extends Terrain {
    @Override
    public float acceptFighter(final Rogue rogue) {
        return Rogue.ROGUE_WOODS_BONUS;
    }

    @Override
    public boolean isCritical() {
        return true;
    }
}
