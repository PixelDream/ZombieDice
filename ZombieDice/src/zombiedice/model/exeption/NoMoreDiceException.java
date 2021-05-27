package zombiedice.model.exeption;

public class NoMoreDiceException extends Exception {

    public NoMoreDiceException() {
        super("No more dice in diceList");
    }

}
