package heroes;

import utility.Coordinate;
import map.GameMap;

public final class HeroFactory {
    private static final int KNIGHT_HP = 900;
    private static final int WIZARD_HP = 400;
    private static final int ROGUE_HP = 600;
    private HeroFactory() {

    }

    private static HeroFactory instance = null;

    public static HeroFactory getInstance() {
        if (instance == null) {
            instance = new HeroFactory();
        }

        return instance;
    }

    public Hero createHero(final String className, final Coordinate coordinate,
                           final GameMap gameMap) {

        switch (className) {
            case "P":
                return new Pyromancer(coordinate, gameMap.getTerrain(coordinate));

            case "K":
                return new Knight(coordinate, gameMap.getTerrain(coordinate));

            case "W":
                return new Wizard(coordinate, gameMap.getTerrain(coordinate));

            case "R":
                return new Rogue(coordinate, gameMap.getTerrain(coordinate));

            default:
                System.out.println("ERROR: Invalid hero class");
                return null;
        }
    }
}
