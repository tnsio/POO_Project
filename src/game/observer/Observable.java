package game.observer;

import game.action.GameAction;

public interface Observable {
    void addObserver(Observer observer);
    void notifyAllObservers(GameAction gameAction);
}
