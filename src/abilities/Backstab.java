package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class Backstab extends Ability {
    private static final int BASE_DAMAGE = 200;
    private static final int SCALING_DAMAGE = 20;
    private static final float ROGUE_MODIFIER = 0.2f;
    private static final float KNIGHT_MODIFIER = -0.1f;
    private static final float PYROMANCER_MODIFIER = 0.25f;
    private static final float WIZARD_MODIFIER = 0.25f;
    private static final int CRITICAL_PERIOD = 3;
    private static final float CRITICAL_MODIFIER = 1.5f;
    private int criticalCounter = 0;


    @Override
    public float getUnmodifiedDamage(final Hero attacker, final Hero victim) {
        float damage = BASE_DAMAGE + SCALING_DAMAGE * attacker.getLevel();
        if (criticalCounter == 0 && attacker.getTerrain().isCritical()) {
            damage *= CRITICAL_MODIFIER;
        }
        return damage;
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
    public void afterCast() {
        criticalCounter = (criticalCounter + 1) % CRITICAL_PERIOD;
    }
}
