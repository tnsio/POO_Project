package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class Paralysis extends Ability {
    private static final int BASE_DAMAGE = 40;
    private static final int SCALING_DAMAGE = 10;
    private static final int EFFECT_DURATION = 3;
    private static final int CRITICAL_EFFECT_DURATION = 6;
    private static final float ROGUE_MODIFIER = -0.1f;
    private static final float KNIGHT_MODIFIER = -0.2f;
    private static final float PYROMANCER_MODIFIER = 0.2f;
    private static final float WIZARD_MODIFIER = 0.25f;


    @Override
    public float getUnmodifiedDamage(final Hero attacker, final Hero victim) {
        return BASE_DAMAGE + attacker.getLevel() * SCALING_DAMAGE;
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

    @Override
    public void afflictStatusEffects(final Hero attacker, final Hero victim) {
        victim.cure();

        int damage = Math.round(getFinalDamage(attacker, victim));
        int duration;
        if (attacker.getTerrain().isCritical()) {
            duration = CRITICAL_EFFECT_DURATION;
        } else {
            duration = EFFECT_DURATION;
        }

        victim.beIncapacitated(duration);
        victim.takeOverTimeDamage(damage, duration);
    }
}
