package attack.visitor;

public interface Fighter {
    float getModifier(FightContext fightContext);
    //TODO: Delete pacea mea sufleteasca
    //     return fightContext.acceptFighter(this);
}
