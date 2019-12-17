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

        BaseGameInput baseGameInput;
        AngelInput angelInput;
        GameInputLoader gameInputLoader;

        gameInputLoader = new GameInputLoader(fs);

        try {
            baseGameInput = gameInputLoader.loadBaseInput();
        } catch (IOException e) {
            System.out.println("ERROR: Something went wrong with creating the game input");
            return;
        }

        try {
            angelInput = gameInputLoader.loadAngelInput(baseGameInput.getNrRounds());
        } catch (IOException e) {
            System.out.println("ERROR: Something went wrong with creating the angel input");
            return;
        }

        GameEngine gameEngine = new GameEngine(baseGameInput, angelInput);
        gameEngine.play();
        try {
            gameEngine.printHeroes(fs);
        } catch (IOException e) {
            System.out.println("ERROR: Can't access input or output files");
        } finally {
            try {
                fs.close();
            } catch (IOException e1) {
                System.out.println("ERROR: Can't close input or output files");
            }
        }
    }
}
