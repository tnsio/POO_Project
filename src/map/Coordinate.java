package map;

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

        }
    }

    @Override
    public String toString() {
        return "(" + horizontal +  ", " + vertical + ")";
    }
}
