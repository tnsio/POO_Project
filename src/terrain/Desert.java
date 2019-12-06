package terrain;

import heroes.Wizard;

public final class Desert extends Terrain {
    @Override
    public float acceptFighter(final Wizard wizard) {
        return Wizard.WIZARD_DESERT_BONUS;
    }
}
