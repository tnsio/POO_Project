package main;

import game.engine.GameEngine;

public final class Main {

    private Main() {

    }

    public static void main(final String[] args) {
        if (args.length != 2) {
            System.out.println("ERROR: Incorrect number of arguments!");
            return;
        }

        GameInput gameInput;
        GameInputLoader gameInputLoader;

        gameInputLoader = new GameInputLoader(args[0], args[1]);
        gameInput = gameInputLoader.load();

        GameEngine gameEngine = new GameEngine(gameInput);
        gameEngine.play();
        gameEngine.printHeroes();
    }
}
