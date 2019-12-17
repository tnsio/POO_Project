package main;

import fileio.FileSystem;
import utility.Coordinate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public final class GameInputLoader {
    private FileSystem fs;
    private static final String ANGEL_DESCRIPTION_DELIMITER = ",";

    GameInputLoader(final FileSystem fs) {
        this.fs = fs;
    }

    public BaseGameInput loadBaseInput() throws IOException {
        final int mapHeight;
        final int mapWidth;
        final int nrHeroes;
        final int nrRounds;
        final ArrayList<String> rawMap = new ArrayList<String>();
        final ArrayList<String> classNames = new ArrayList<String>();
        final ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        final ArrayList<String> movesList = new ArrayList<String>();

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

        return new BaseGameInput(mapHeight, mapWidth, rawMap, classNames, coordinates,
                nrRounds, movesList);
    }

    // TODO load angel input
    public AngelInput loadAngelInput(final int nrRounds) throws IOException {
        ArrayList<ArrayList<String>> angelNames = new ArrayList<>();
        ArrayList<ArrayList<Integer>> horizontalCoordinates = new ArrayList<>();
        ArrayList<ArrayList<Integer>> verticalCoordinates = new ArrayList<>();
        for (int roundIter = 0; roundIter < nrRounds; roundIter++) {
            int nrAngels = fs.nextInt();
            angelNames.add(new ArrayList<>());
            horizontalCoordinates.add(new ArrayList<>());
            verticalCoordinates.add(new ArrayList<>());

            for (int angelIter = 0; angelIter < nrAngels; angelIter++) {
                String angelDescription = fs.nextWord();
                StringTokenizer stringTokenizer = new StringTokenizer(angelDescription,
                        ANGEL_DESCRIPTION_DELIMITER);
                angelNames.get(roundIter).add(stringTokenizer.nextToken());
                Integer horizontalCoordinate = Integer.parseInt(stringTokenizer.nextToken());
                Integer verticalCoordinate = Integer.parseInt(stringTokenizer.nextToken());
                horizontalCoordinates.get(roundIter).add(horizontalCoordinate);
                verticalCoordinates.get(roundIter).add(verticalCoordinate);
            }
        }

        return new AngelInput(angelNames, horizontalCoordinates, verticalCoordinates);
    }
}
