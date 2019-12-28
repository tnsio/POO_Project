package game.action;

import angels.Angel;
import heroes.Hero;

public final class HitAction implements GameAction {
    private final StringBuilder description = new StringBuilder();
    public HitAction(final Angel angel, final Hero hero) {
        description.append(angel.getAngelName());
        description.append(" hit ");
        description.append(hero.getHeroName());
        description.append(' ');
        description.append(hero.getId());
        description.append(System.lineSeparator());
    }

    public StringBuilder getDescription() {
        return description;
    }
}
