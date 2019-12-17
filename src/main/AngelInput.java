package main;

import java.util.ArrayList;

public final class AngelInput {
    private ArrayList<ArrayList<String>> angelNames;
    private ArrayList<ArrayList<Integer>> horizontalCoordinates;
    private ArrayList<ArrayList<Integer>> verticalCoordinates;

    public AngelInput(final ArrayList<ArrayList<String>> angelNames,
                      final ArrayList<ArrayList<Integer>> horizontalCoordinates,
                      final ArrayList<ArrayList<Integer>> verticalCoordinates) {
        this.angelNames = angelNames;
        this.horizontalCoordinates = horizontalCoordinates;
        this.verticalCoordinates = verticalCoordinates;
    }

    public ArrayList<ArrayList<String>> getAngelNames() {
        return angelNames;
    }

    public ArrayList<ArrayList<Integer>> getHorizontalCoordinates() {
        return horizontalCoordinates;
    }

    public ArrayList<ArrayList<Integer>> getVerticalCoordinates() {
        return verticalCoordinates;
    }
}
