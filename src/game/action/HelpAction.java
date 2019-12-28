package game.action;

import angels.Angel;
import heroes.Hero;

public final class HelpAction implements GameAction {
    private final StringBuilder description = new StringBuilder();
    public HelpAction(final Angel angel, final Hero hero) {
        description.append(angel.getAngelName());
        description.append(" helped ");
        description.append(hero.getHeroName());
        description.append(' ');
        description.append(hero.getId());
        description.append(System.lineSeparator());
    }

    public StringBuilder getDescription() {
        return description;
    }
}
