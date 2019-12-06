package terrain;

import heroes.Pyromancer;

public final class Volcanic extends Terrain {
    @Override
    public float acceptFighter(final Pyromancer pyromancer) {
        return Pyromancer.PYROMANCER_VOLCANIC_BONUS;
    }
}
