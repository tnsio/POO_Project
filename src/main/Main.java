package main;

import fileio.FileSystem;
import game.engine.GameEngine;

import java.io.IOException;


public final class Main {

    private Main() {

    }

    public static void main(final String[] args) {
        if (args.length != 2) {
            System.out.println("ERROR: Incorrect number of arguments!");
            return;
        }
        FileSystem fs;

        try {
            fs = new FileSystem(args[0], args[1]);
        } catch (IOException e) {
            System.out.println("ERROR: Can't open input or output files.");
            return;
        }

        GameInput gameInput;
        GameInputLoader gameInputLoader;

        gameInputLoader = new GameInputLoader(fs);
        gameInput = gameInputLoader.load();

        GameEngine gameEngine = new GameEngine(gameInput);
        gameEngine.play();
        try {
            gameEngine.printHeroes(fs);
        } catch (IOException e) {
            System.out.println("ERROR: Can't access input or output files");
            return;
        } finally {
            try {
                fs.close();
            } catch (IOException e1) {
                System.out.println("ERROR: Can't close input or output files");
            }
        }
    }
}
