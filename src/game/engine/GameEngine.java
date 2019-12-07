package game.engine;

import fileio.FileSystem;
import heroes.Hero;
import heroes.HeroFactory;
import main.GameInput;
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

    public GameEngine(final GameInput gameInput) {
        map = new GameMap(gameInput.getMapHeight(), gameInput.getMapWidth(),
                gameInput.getRawMap());

        nrRounds = gameInput.getNrRounds();

        heroes = new ArrayList<Hero>();
        int nrHeroes = gameInput.getClassNames().size();
        HeroFactory heroFactory = HeroFactory.getInstance();
        ArrayList<String> classNames = gameInput.getClassNames();
        ArrayList<Coordinate> coordinates = gameInput.getCoordinates();

        for (int heroIter = 0; heroIter < nrHeroes; heroIter++) {
            heroes.add(heroFactory.createHero(classNames.get(heroIter), coordinates.get(heroIter),
                    map));
        }

        heroesByCoordinates = new HashMap<Coordinate, ArrayList<Hero>>();
        placeHeroes();
        moves = gameInput.getMoves();
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
            hero.setTerrain(map.getTerrain(hero.getPosition()));
        }
    }

    private void moveHeroes() {
        int nrHeroes = heroes.size();
        String currentMoves = moves.get(currentRound);
        for (int heroIter = 0; heroIter < nrHeroes; heroIter++) {
            Hero hero = heroes.get(heroIter);
            if (!hero.isIncapacitated() && hero.isAlive()) {
                hero.getPosition().move(currentMoves.charAt(heroIter));
            }
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

        hero1.addStatusEffects(hero2);
        hero2.addStatusEffects(hero1);

        hero1.afterAttack();
        hero2.afterAttack();
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

    // TODO Make up your mind if you need to delete this
    public void printHeroesToStdout() {
        for (Hero hero : heroes) {
            System.out.println(hero);
        }
    }
}
