package zombiedice.model;

import zombiedice.controller.GameController;
import zombiedice.model.exeption.NoMoreDiceException;
import zombiedice.utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class DiceManager {

    private List<Dice> diceList;

    private int greenDice;
    private int yellowDice;
    private int redDice;

    public DiceManager(Difficulty difficulty) {
        greenDice = difficulty.getGreenDice();
        yellowDice = difficulty.getYellowDice();
        redDice = difficulty.getRedDice();

        generateDices();
    }

    private void generateDices() {
        diceList = new ArrayList<>();

        for (int i = 0; i < greenDice; i++) diceList.add(new Dice(DiceColor.GREEN));
        for (int i = 0; i < yellowDice; i++) diceList.add(new Dice(DiceColor.YELLOW));
        for (int i = 0; i < redDice; i++) diceList.add(new Dice(DiceColor.RED));
    }

    /**
     * Manage dice during the randomisation
     *
     * @return path of random Dice
     * @throws NoMoreDiceException
     */
    public String getRandomDice() throws NoMoreDiceException {
        if (diceList.isEmpty()) throw new NoMoreDiceException();

        int rand = new RandomUtils(0, diceList.size()).nextInt();

        Dice dice = diceList.get(rand);
        DiceFace diceFace = dice.getDiceFaces().get(new RandomUtils(0, dice.getDiceFaces().size()).nextInt());


        if (!diceFace.equals(DiceFace.FOOT)) {
            // Switch with arrow functions (Java 14) [Switch Expression]

            switch (dice.getDiceColor()) {
                case RED -> redDice--;
                case GREEN -> greenDice--;
                case YELLOW -> yellowDice--;
            }

//            switch (dice.getDiceColor()) {
//                case RED:
//                    redDice--;
//                    break;
//                case GREEN:
//                    greenDice--;
//                    break;
//                case YELLOW:
//                    yellowDice--;
//                    break;
//            }

            diceList.remove(rand);
        }

        if (diceFace.equals(DiceFace.BRAIN)) GameController.currentBrain++;
        if (diceFace.equals(DiceFace.FIRE)) GameController.currentFire++;

        return diceFace.getPath(dice.getDiceColor());
    }

    public String getGreenDice() {
        return String.valueOf(greenDice);
    }

    public String getYellowDice() {
        return String.valueOf(yellowDice);
    }

    public String getRedDice() {
        return String.valueOf(redDice);
    }
}
