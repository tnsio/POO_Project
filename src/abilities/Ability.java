package abilities;

import attack.visitor.FightContext;
import heroes.Hero;

public abstract class Ability implements FightContext {
    public abstract int getBaseDamage(Hero attacker, Hero victim);

    /**
     * Returns the unrounded damage of an ability after class modifiers are applied.
     * @param attacker
     * @param victim
     * @return
     */
    public float getDamage(final Hero attacker, final Hero victim) {
        return getBaseDamage(attacker, victim)
                * (1f + victim.getModifier(this));
    }

    /**
     * Returns the unrounded real damage of an ability.
     * @param attacker
     * @param victim
     * @return
     */
    public float getRealDamage(final Hero attacker, final Hero victim) {
        return getDamage(attacker, victim) * (1f + attacker.getModifier(attacker.getTerrain()));
    }

    /**
     * Returns the unrounded real damage and changes the state of ability when necessary.
     * @param attacker
     * @param victim
     * @return
     */
    public float cast(final Hero attacker, final Hero victim) {
        return getRealDamage(attacker, victim);
    }

}
