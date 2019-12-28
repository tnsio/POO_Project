package main;

import utility.Coordinate;

import java.util.ArrayList;

public final class AngelInput {
    private ArrayList<ArrayList<String>> angelNames;
    private ArrayList<ArrayList<Coordinate>> coordinates;

    public AngelInput(final ArrayList<ArrayList<String>> angelNames,
                      final ArrayList<ArrayList<Coordinate>> coordinates) {
        this.angelNames = angelNames;
        this.coordinates = coordinates;
    }

    public ArrayList<ArrayList<String>> getAngelNames() {
        return angelNames;
    }

    public ArrayList<ArrayList<Coordinate>> getCoordinates() {
        return coordinates;
    }
}
