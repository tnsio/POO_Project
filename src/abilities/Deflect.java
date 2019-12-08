package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class Deflect extends Ability {
    private static final float BASE_PERCENT = 0.35f;
    private static final float SCALING_PERCENT = 0.02f;
    private static final float MAX_PERCENT = 0.7f;
    private static final float ROGUE_MODIFIER = 0.2f;
    private static final float KNIGHT_MODIFIER = 0.4f;
    private static final float PYROMANCER_MODIFIER = 0.3f;
    private static final float WIZARD_MODIFIER = -1f;

    @Override
    public float getUnmodifiedDamage(final Hero attacker, final Hero victim) {
        if (victim.isImmuneToDeflect()) {
            return 0;
        } else {
            float percent = Math.min(MAX_PERCENT,
                    BASE_PERCENT + attacker.getLevel() * SCALING_PERCENT);
            return percent * victim.getDeflectableDamage(attacker);
        }
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
