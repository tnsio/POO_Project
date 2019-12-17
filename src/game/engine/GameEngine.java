package game.engine;

import fileio.FileSystem;
import heroes.Hero;
import heroes.HeroFactory;
import main.AngelInput;
import main.BaseGameInput;
import map.GameMap;
import utility.Coordinate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class GameEngine {
    private GameMap map;
    private ArrayList<Hero> heroes;
    private Map<Coordinate, ArrayList<Hero>> heroesByCoordinates;
    private final int nrRounds;
    private int currentRound = 0;
    private final ArrayList<String> moves;

    public GameEngine(final BaseGameInput baseGameInput) {
        map = new GameMap(baseGameInput.getMapHeight(), baseGameInput.getMapWidth(),
                baseGameInput.getRawMap());

        heroesByCoordinates = new HashMap<Coordinate, ArrayList<Hero>>();
        nrRounds = baseGameInput.getNrRounds();

        heroes = new ArrayList<Hero>();
        int nrHeroes = baseGameInput.getClassNames().size();
        HeroFactory heroFactory = HeroFactory.getInstance();
        ArrayList<String> classNames = baseGameInput.getClassNames();
        ArrayList<Coordinate> coordinates = baseGameInput.getCoordinates();

        for (int heroIter = 0; heroIter < nrHeroes; heroIter++) {
            heroes.add(heroFactory.createHero(classNames.get(heroIter), coordinates.get(heroIter),
                    map));
        }

        placeHeroes();
        moves = baseGameInput.getMoves();
    }

    public GameEngine(final  BaseGameInput baseGameInput, final AngelInput angelInput) {
        this(baseGameInput);
        //TODO Finish the game engine
    }

    private void placeHeroes() {
        heroesByCoordinates.clear();
        for (Hero hero: heroes) {
            if (!hero.isAlive()) {
                continue;
            }

            if (!heroesByCoordinates.containsKey(hero.getPosition())) {
                heroesByCoordinates.put(hero.getPosition(), new ArrayList<Hero>());
            }

            heroesByCoordinates.get(hero.getPosition()).add(hero);
            hero.setTerrainFrom(map);
        }
    }

    private void moveHeroes() {
        int nrHeroes = heroes.size();
        String currentMoves = moves.get(currentRound);
        for (int heroIter = 0; heroIter < nrHeroes; heroIter++) {
            Hero hero = heroes.get(heroIter);
            hero.move(currentMoves.charAt(heroIter));
        }

        placeHeroes();
    }

    private void buryHeroes() {
        for (Map.Entry<Coordinate, ArrayList<Hero>> tile: heroesByCoordinates.entrySet()) {
            tile.getValue().removeIf((Hero hero) -> !hero.isAlive());
        }
    }

    private void applyAllStatusEffects() {
        for (Hero hero: heroes) {
            if (hero.isAlive()) {
                hero.applyStatusEffects();
            }
        }
    }

    private void fightAll() {
        for (Map.Entry<Coordinate, ArrayList<Hero>> entry: heroesByCoordinates.entrySet()) {
           if (entry.getValue().size() > 2) {
               System.out.println("ERROR: More than two players on one tile.");
           }

           if (entry.getValue().size() == 2) {
               fight(entry.getValue().get(0), entry.getValue().get(1));
           }
        }
    }

    private void fight(final Hero hero1, final Hero hero2) {
        int damageFrom1To2 = hero1.getAttackDamage(hero2);
        int damageFrom2To1 = hero2.getAttackDamage(hero1);

        hero1.takeDamage(damageFrom2To1);
        hero2.takeDamage(damageFrom1To2);

        hero1.afflictStatusEffects(hero2);
        hero2.afflictStatusEffects(hero1);

        hero1.afterAttack();
        hero2.afterAttack();

        hero1.loot(hero2);
        hero2.loot(hero1);
    }

    public void play() {
        for (currentRound = 0; currentRound < nrRounds; currentRound++) {
            applyAllStatusEffects();
            buryHeroes();
            moveHeroes();
            fightAll();
            buryHeroes();
        }

    }

    public void printHeroes(final FileSystem fs) throws IOException {
        for (Hero hero: heroes) {
            fs.writeWord(hero.toString());
            fs.writeNewLine();
        }
    }

    public void printHeroesToStdout() {
        for (Hero hero : heroes) {
            System.out.println(hero);
        }
    }

    // Used for debugging, and will be updated and used for debugging in stage 2
    @Override
    public String toString() {
        StringBuilder gameStateString = new StringBuilder("Round " + currentRound
                + System.lineSeparator());
        for (int rowIter = 0; rowIter < map.getHeight(); rowIter++) {
            for (int colIter = 0; colIter < map.getWidth(); colIter++) {
                Coordinate coordinate = new Coordinate(colIter, rowIter);
                ArrayList<Hero> heroesHere = heroesByCoordinates.get(coordinate);

                if (heroesHere == null) {
                    gameStateString.append("-  ");
                    continue;
                }

                switch (heroesHere.size()) {
                    case 0:
                        gameStateString.append("-  ");
                        break;
                    case 1:
                        gameStateString.append(heroesHere.get(0).getIdentifier() + "  ");
                        break;
                    case 2:
                        gameStateString.append(heroesHere.get(0).getIdentifier()
                                + heroesHere.get(1).getIdentifier() + " ");
                        break;
                    default:
                        System.out.println("ERROR: Too many heroes on one tile");
                }

            }
            gameStateString.append(System.lineSeparator());
        }
        return gameStateString.toString();
    }
}
