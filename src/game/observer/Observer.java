package game.observer;

import game.action.GameAction;

public interface Observer {
    void update(GameAction gameAction);
}
