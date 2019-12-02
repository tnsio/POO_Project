package main;

import java.util.ArrayList;

// Class which holds minimally processed game input
public class GameInput {
    private final int mapHeight;
    private final int mapWidth;
    private final ArrayList<String> rawMap;
    private final ArrayList<String> classNames;
    private final ArrayList<Integer> horizontalCoordinates;
    private final ArrayList<Integer> verticalCoordinates;
    private final ArrayList<String> moves;

    public GameInput(final int mapHeight, final int mapWidth, final ArrayList<String> rawMap,
                     final ArrayList<String> classNames,
                     final ArrayList<Integer> horizontalCoordinates,
                     final ArrayList<Integer> verticalCoordinates,
                     final ArrayList<String> moves) {
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.rawMap = rawMap;
        this.classNames = classNames;
        this.horizontalCoordinates = horizontalCoordinates;
        this.verticalCoordinates = verticalCoordinates;
        this.moves = moves;
    }
}
