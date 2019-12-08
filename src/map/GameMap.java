package map;

import terrain.Terrain;
import terrain.TerrainFactory;
import utility.Coordinate;

import java.util.ArrayList;

public final class GameMap {
    private final int height;
    private final int width;
    private final ArrayList<String> rawMap;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public GameMap(final int height, final int width, final ArrayList<String> rawMap) {
        this.height = height;
        this.width = width;
        this.rawMap = rawMap;
    }

    public Terrain getTerrain(final Coordinate coordinate) {
        TerrainFactory terrainFactory = TerrainFactory.getInstance();
        char terrainIdentifier = rawMap.get(coordinate.getVertical())
                .charAt(coordinate.getHorizontal());

        return terrainFactory.makeTerrain(terrainIdentifier);
    }
}
