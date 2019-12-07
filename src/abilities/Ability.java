package abilities;

import attack.visitor.FightContext;
import heroes.Hero;

public abstract class Ability implements FightContext {
    public abstract int getBaseDamage(Hero attacker, Hero victim);
    public  void applyStatus(final Hero attacker, final Hero victim) {

    }

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
    public float getFinalDamage(final Hero attacker, final Hero victim) {
        return getDamage(attacker, victim) * (1f + attacker.getModifier(attacker.getTerrain()));
    }

    /**
     * Executes the necessary modifications to the state of a ability after cast.
     */
    public void afterCast() {

    }

    /**
     * Adds status effects on the victim if necessary.
     * @param attacker
     * @param victim
     */
    public void addStatusEffects(final Hero attacker, final Hero victim) {

    }
}
