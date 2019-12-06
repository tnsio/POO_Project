package utility;

import java.util.Objects;

public final class Coordinate {
    private int horizontal;
    private int vertical;

    public Coordinate(final int horizontal, final int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public void moveUp() {
        vertical--;
    }

    public void moveDown() {
        vertical++;
    }

    public void moveLeft() {
       horizontal--;
    }

    public void moveRight() {
        horizontal++;
    }

    public void move(final char moveChar) {
        switch (moveChar) {
            case 'U':
                moveUp();
                break;
            case 'D':
                moveDown();
                break;
            case 'L':
                moveLeft();
                break;
            case 'R':
                moveRight();
                break;
            case '_':
                break;
            default:
                // TODO folosim sau nu exceptii
                break;

        }
    }

    @Override
    public String toString() {
        return "(" + horizontal +  ", " + vertical + ")";
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return horizontal == that.horizontal &&
                vertical == that.vertical;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontal, vertical);
    }
}
