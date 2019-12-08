package main;

import fileio.FileSystem;
import utility.Coordinate;

import java.util.ArrayList;

public final class GameInputLoader {
    private FileSystem fs;

    GameInputLoader(final FileSystem fs) {
        this.fs = fs;
    }

    public GameInput load() {
        final int mapHeight;
        final int mapWidth;
        final int nrHeroes;
        final int nrRounds;
        final ArrayList<String> rawMap = new ArrayList<String>();
        final ArrayList<String> classNames = new ArrayList<String>();
        final ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        final ArrayList<String> movesList = new ArrayList<String>();

        try {
            mapHeight = fs.nextInt();
            mapWidth = fs.nextInt();

            for (int rowIter = 0; rowIter < mapHeight; rowIter++) {
                rawMap.add(fs.nextWord());
            }

            nrHeroes = fs.nextInt();

            for (int heroIter = 0; heroIter < nrHeroes; heroIter++) {
                int horizontal, vertical;

                classNames.add(fs.nextWord());

                vertical = fs.nextInt();
                horizontal = fs.nextInt();

                coordinates.add(new Coordinate(horizontal, vertical));
            }

            nrRounds = fs.nextInt();

            for (int roundIter = 0; roundIter < nrRounds; roundIter++) {
                movesList.add(fs.nextWord());
            }

            return new GameInput(mapHeight, mapWidth, rawMap, classNames, coordinates,
                    nrRounds, movesList);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }

    }
}
