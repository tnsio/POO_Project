package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class Fireblast extends Ability {
    private static final int BASE_DAMAGE = 350;
    private static final int SCALING_DAMAGE = 50;
    private static final float ROGUE_MODIFIER = -0.2f;
    private static final float KNIGHT_MODIFIER = 0.2f;
    private static final float PYROMANCER_MODIFIER = -0.1f;
    private static final float WIZARD_MODIFIER = 0.05f;

    @Override
    public float getUnmodifiedDamage(final Hero attacker, final Hero victim) {
        return BASE_DAMAGE + SCALING_DAMAGE * attacker.getLevel();
    }

    @Override
    public float acceptFighter(final Rogue rogue) {
        return ROGUE_MODIFIER;
    }

    @Override
    public float acceptFighter(final Knight knight) {
        return KNIGHT_MODIFIER;
    }

    @Override
    public float acceptFighter(final Pyromancer pyromancer) {
        return PYROMANCER_MODIFIER;
    }

    @Override
    public float acceptFighter(final Wizard wizard) {
        return WIZARD_MODIFIER;
    }

}
