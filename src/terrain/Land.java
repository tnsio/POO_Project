package terrain;

import heroes.Knight;

public final class Land extends Terrain {
    @Override
    public float acceptFighter(final Knight knight) {
        return Knight.KNIGHT_LAND_BONUS;
    }
}
