package angels;

import utility.Coordinate;

public abstract class Angel {
    private Coordinate position;

    public Angel(final Coordinate coordinate) {
        position = coordinate;
    }

    /**
     * Returns the name of the specific type of angel.
     * @return
     */
    public abstract String getAngelName();

    public final Coordinate getPosition() {
        return position;
    }
}
