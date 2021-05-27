package zombiedice.model;

import java.util.ArrayList;
import java.util.List;

public class Dice {

    private final List<DiceFace> diceFaces;
    private final DiceColor diceColor;

    public Dice(DiceColor diceColor) {
        diceFaces = new ArrayList<>();
        this.diceColor = diceColor;

        for (int i = 0; i < diceColor.getBrain(); i++) diceFaces.add(DiceFace.BRAIN);
        for (int i = 0; i < diceColor.getFoot(); i++) diceFaces.add(DiceFace.FOOT);
        for (int i = 0; i < diceColor.getFire(); i++) diceFaces.add(DiceFace.FIRE);
    }

    public List<DiceFace> getDiceFaces() {
        return diceFaces;
    }

    public DiceColor getDiceColor() {
        return diceColor;
    }
}
