package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class Drain extends Ability {
    private static final float BASE_PERCENT = 0.2f;
    private static final float PERCENT_HANDICAP = 0.3f;
    private static final float SCALING_PERCENT = 0.05f;
    private static final float ROGUE_MODIFIER = -0.2f;
    private static final float KNIGHT_MODIFIER = 0.2f;
    private static final float PYROMANCER_MODIFIER = -0.1f;
    private static final float WIZARD_MODIFIER = 0.05f;

    @Override
    public float getUnmodifiedDamage(final Hero attacker, final Hero victim) {
        float percent = BASE_PERCENT + attacker.getLevel() * SCALING_PERCENT;
        float baseHp = Math.min(PERCENT_HANDICAP * victim.getMaxHp(), victim.getCurrentHp());
        return percent * baseHp;
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
