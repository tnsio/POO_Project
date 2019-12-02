package map;

import java.util.ArrayList;

public final class Map {
    private final int height;
    private final int width;
    private final ArrayList<String> rawMap;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Map(final int height, final int width, final ArrayList<String> rawMap) {
        this.height = height;
        this.width = width;
        this.rawMap = rawMap;
    }
}
