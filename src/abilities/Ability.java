package abilities;

import attack.visitor.FightContext;
import heroes.Hero;

public abstract class Ability implements FightContext {
    public abstract float getUnmodifiedDamage(Hero attacker, Hero victim);
    public  void applyStatus(final Hero attacker, final Hero victim) {

    }

    /**
     * Returns the unrounded damage of an ability after class modifiers are applied.
     * @param attacker
     * @param victim
     * @return
     */
    public float getDamageOnVictim(final Hero attacker, final Hero victim) {
        return getUnmodifiedDamage(attacker, victim)
                * (1f + victim.getModifier(this));
    }

    /**
     * Returns the unrounded real damage of an ability.
     * @param attacker
     * @param victim
     * @return
     */
    public float getFinalDamage(final Hero attacker, final Hero victim) {
        return getDamageOnTerrain(attacker, victim) * (1f + victim.getModifier(this));
    }

    /**
     * Returns the unrounded damage of an ability after the land modifiers are applied.
     * @param attacker
     * @param victim
     * @return
     */
    public float getDamageOnTerrain(final Hero attacker, final Hero victim) {
        return getUnmodifiedDamage(attacker, victim) * (1f + attacker.getLandModifier());
    }

    /**
     * Executes the necessary modifications to the state of a ability after cast.
     */
    public void afterCast() {

    }

    /**
     * Adds status effects on the victim if necessary.
     * Be sure to cure the victim first if you override this method.
     * @param attacker
     * @param victim
     */
    public void afflictStatusEffects(final Hero attacker, final Hero victim) {

    }
}
