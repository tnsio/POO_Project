package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class Ignite extends Ability {
    private static final int BASE_DAMAGE = 150;
    private static final int SCALING_DAMAGE = 20;
    private static final int EFFECT_DURATION = 2;
    private static final int EFFECT_DAMAGE = 50;
    private static final int EFFECT_SCALING_DAMAGE = 30;
    private static final float ROGUE_MODIFIER = -0.2f;
    private static final float KNIGHT_MODIFIER = 0.2f;
    private static final float PYROMANCER_MODIFIER = -0.1f;
    private static final float WIZARD_MODIFIER = 0.05f;

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
        int damage = EFFECT_DAMAGE + attacker.getLevel() * EFFECT_SCALING_DAMAGE;
        int finalDamage = Math.round(damage * (1f + victim.getModifier(this))
                * (1f + attacker.getModifier(attacker.getTerrain())));
        victim.takeOverTimeDamage(finalDamage, EFFECT_DURATION);
    }
}
