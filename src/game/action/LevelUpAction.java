package game.action;

import heroes.Hero;

public final class LevelUpAction implements GameAction {
    private final StringBuilder description = new StringBuilder();
    public LevelUpAction(final Hero hero, final int previousLevel) {
        for (int level = previousLevel + 1; level < hero.getLevel(); level++) {
            description.append(hero.getHeroName());
            description.append(' ');
            description.append(hero.getId());
            description.append(" reached level ");
            description.append(level);
            description.append(System.lineSeparator());
        }
    }

    public StringBuilder getDescription() {
        return description;
    }
}
