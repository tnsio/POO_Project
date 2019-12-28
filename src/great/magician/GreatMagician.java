package great.magician;

import game.action.GameAction;
import game.observer.Observer;

public final class GreatMagician implements Observer {
    private StringBuilder log = new StringBuilder();
    private static GreatMagician instance = new GreatMagician();

    private GreatMagician() {

    }

    public static GreatMagician getInstance() {
        return instance;
    }

    public StringBuilder getLog() {
        return log;
    }

    public void update(final GameAction gameAction) {
        log.append(gameAction.getDescription());
    }

    public void addRoundHeader(final int roundNr) {
        log.append("~~ Round ");
        log.append(roundNr);
        log.append("~~");
        log.append(System.lineSeparator());
    }

    public void addRoundFooter() {
        log.append(System.lineSeparator());
    }
}
