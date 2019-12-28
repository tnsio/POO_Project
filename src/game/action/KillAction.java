package game.action;

import heroes.Hero;

public final  class KillAction implements GameAction {
    private final StringBuilder description = new StringBuilder();

    public KillAction(final Hero attacker, final Hero victim) {
        description.append("Player ");
        description.append(victim.getHeroName());
        description.append(' ');
        description.append(victim.getId());
        description.append(" was killed by ");
        description.append(attacker.getHeroName());
        description.append(' ');
        description.append(attacker.getId());
        description.append(System.lineSeparator());
    }

    public StringBuilder getDescription() {
        return description;
    }
}
