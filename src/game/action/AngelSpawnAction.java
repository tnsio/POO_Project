package game.action;

import angels.Angel;

public final class AngelSpawnAction implements GameAction {
    private final StringBuilder description = new StringBuilder();
    public AngelSpawnAction(final Angel angel) {
        description.append("Angel ");
        description.append(angel.getAngelName());
        description.append(" was spawned at ");
        description.append(angel.getPosition());
        description.append(System.lineSeparator());
    }

    public StringBuilder getDescription() {
        return description;
    }
}
