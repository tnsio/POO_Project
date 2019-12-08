package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class Execute extends Ability {
    private static final int BASE_DAMAGE = 200;
    private static final int SCALING_DAMAGE = 30;
    private static final float ROGUE_MODIFIER = 0.15f;
    private static final float KNIGHT_MODIFIER = 0f;
    private static final float PYROMANCER_MODIFIER = 0.10f;
    private static final float WIZARD_MODIFIER = -0.20f;
    private static final float BASE_HP_LIMIT = 0.20f;
    private static final float MAX_HP_LIMIT = 0.40f;
    private static final float HP_LIMIT_SCALING = 0.01f;



    @Override
    public float getUnmodifiedDamage(final Hero attacker, final Hero victim) {
        if (executes(attacker, victim)) {
            return victim.getCurrentHp();
        } else {
            return BASE_DAMAGE + victim.getLevel() * SCALING_DAMAGE;
        }
    }

    @Override
    public float getDamageOnVictim(final Hero attacker, final Hero victim) {
        if (executes(attacker, victim)) {
            return getUnmodifiedDamage(attacker, victim);
        } else {
            return getUnmodifiedDamage(attacker, victim)
                    * (1f + victim.getModifier(this));
        }
    }

    @Override
    public float getDamageOnTerrain(final Hero attacker, final Hero victim) {
        if (executes(attacker, victim)) {
            return getUnmodifiedDamage(attacker, victim);
        } else {
            return getUnmodifiedDamage(attacker, victim)
                    * (1f + attacker.getLandModifier());
        }
    }

    @Override
    public float getFinalDamage(final Hero attacker, final Hero victim) {
        if (executes(attacker, victim)) {
            return getUnmodifiedDamage(attacker, victim);
        } else {
            return getDamageOnVictim(attacker, victim)
                    * (1f + attacker.getLandModifier());
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

    private boolean executes(final Hero attacker, final Hero victim) {
        float hpLimit = Math.min(BASE_HP_LIMIT + attacker.getLevel() * HP_LIMIT_SCALING,
                MAX_HP_LIMIT);
        return victim.getCurrentHp() <= Math.round(hpLimit * victim.getMaxHp());
    }
}
