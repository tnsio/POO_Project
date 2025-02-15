package main;

import utility.Coordinate;

import java.util.ArrayList;

// Class which holds minimally processed game input
public final class BaseGameInput {
    private final int mapHeight;
    private final int mapWidth;
    private final int nrRounds;
    private final ArrayList<String> rawMap;
    private final ArrayList<String> classNames;
    private final ArrayList<Coordinate> coordinates;
    private final ArrayList<String> moves;

    public BaseGameInput(final int mapHeight, final int mapWidth, final ArrayList<String> rawMap,
                         final ArrayList<String> classNames,
                         final ArrayList<Coordinate> coordinates,
                         final int nrRounds,
                         final ArrayList<String> moves) {
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.rawMap = rawMap;
        this.classNames = classNames;
        this.coordinates = coordinates;
        this.nrRounds = nrRounds;
        this.moves = moves;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getNrRounds() {
        return nrRounds;
    }

    public ArrayList<String> getRawMap() {
        return rawMap;
    }

    public ArrayList<String> getClassNames() {
        return classNames;
    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }

    public ArrayList<String> getMoves() {
        return moves;
    }
}
