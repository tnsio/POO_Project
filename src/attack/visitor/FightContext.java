package attack.visitor;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public interface FightContext {
    float acceptFighter(Knight knight);
    float acceptFighter(Pyromancer pyromancer);
    float acceptFighter(Rogue rogue);
    float acceptFighter(Wizard wizard);
}
