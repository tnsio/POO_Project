package terrain;

import java.util.HashMap;
import java.util.Map;

public final class TerrainFactory {
    private static TerrainFactory instance = null;
    private final Map<Character, Terrain> terrainByLetter = new HashMap<>();

    private TerrainFactory() {

    }

    private void initializeTerrain() {
        terrainByLetter.put('L', new Land());
        terrainByLetter.put('D', new Desert());
        terrainByLetter.put('V', new Volcanic());
        terrainByLetter.put('W', new Woods());
    };

    public static TerrainFactory getInstance() {
        if (instance == null) {
            instance = new TerrainFactory();
            instance.initializeTerrain();
        }

        return instance;
    }

    public Terrain makeTerrain(final char terrainIdentifier) {
        return terrainByLetter.get(terrainIdentifier);
    }
}
