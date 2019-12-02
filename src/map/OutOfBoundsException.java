package map;

public class OutOfBoundsException extends Exception {
    public OutOfBoundsException(final Coordinate coordinate, final Map map) {
        super("Invalid coordinate " + " in map of dimensions "
                + map.getHeight() + " and " + map.getWidth() + ".");
    }
}
