package terrain;

import attack.visitor.FightContext;

public class Terrain implements FightContext {
    Terrain() {

    }

    /**
     * Returns true if Rogue can critically strike on this type of terrain.
     *
     * @return
     */
    public boolean isCritical() {
        return false;
    }

}
