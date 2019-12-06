package attack.visitor;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public interface FightContext {
    default float acceptFighter(Knight knight) {
        return 0f;
    }

    default float acceptFighter(Pyromancer pyromancer) {
        return 0f;
    }

    default float acceptFighter(Rogue rogue) {
        return 0f;
    }

    default float acceptFighter(Wizard wizard) {
        return 0f;
    }

}
