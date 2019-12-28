package heroes;

import utility.Coordinate;
import map.GameMap;

public final class HeroFactory {
    private HeroFactory() {

    }

    private static HeroFactory instance = null;

    public static HeroFactory getInstance() {
        if (instance == null) {
            instance = new HeroFactory();
        }

        return instance;
    }

    public Hero createHero(final int id, final String className, final Coordinate coordinate,
                           final GameMap gameMap) {

        switch (className) {
            case "P":
                return new Pyromancer(id, coordinate, gameMap.getTerrain(coordinate));

            case "K":
                return new Knight(id, coordinate, gameMap.getTerrain(coordinate));

            case "W":
                return new Wizard(id, coordinate, gameMap.getTerrain(coordinate));

            case "R":
                return new Rogue(id, coordinate, gameMap.getTerrain(coordinate));

            default:
                System.out.println("ERROR: Invalid hero class");
                return null;
        }
    }
}
