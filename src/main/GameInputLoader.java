package main;

import fileio.FileSystem;

import java.util.ArrayList;

public final class GameInputLoader {
    private final String mInputPath;
    private final String mOutputPath;

    GameInputLoader(final String inputPath, final String outputPath) {
        mInputPath = inputPath;
        mOutputPath = outputPath;
    }

    public GameInput load() {
        final int mapHeight;
        final int mapWidth;
        final int nrHeroes;
        final ArrayList<String> rawMap = new ArrayList<String>();
        final ArrayList<String> classNames = new ArrayList<String>();
        final ArrayList<Integer> horizontalCoordinates = new ArrayList<Integer>();
        final ArrayList<Integer> verticalCoordinates = new ArrayList<Integer>();
        final ArrayList<String> movesList = new ArrayList<String>();

        try {
            FileSystem fs = new FileSystem(mInputPath, mOutputPath);

            mapHeight = fs.nextInt();
            mapWidth = fs.nextInt();

            for (int rowIter = 0; rowIter < mapHeight; rowIter++) {
                rawMap.add(fs.nextWord());
            }

            nrHeroes = fs.nextInt();

            for (int heroIter = 0; heroIter < nrHeroes; heroIter++) {
                classNames.add(fs.nextWord());
                horizontalCoordinates.add(fs.nextInt());
                verticalCoordinates.add(fs.nextInt());
            }

            fs.close();

            return new GameInput(mapHeight, mapWidth, rawMap,
                    classNames,
                    horizontalCoordinates,
                    verticalCoordinates,
                    movesList);
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }

    }
}
