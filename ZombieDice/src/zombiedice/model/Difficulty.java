package zombiedice.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.Optional;

public enum Difficulty {
    EASY("Facile", 8, 3, 2), NORMAL("Normal", 6, 4, 3), HARD("Difficile", 4, 5, 4);

    private final String difficulty;

    private final int greenDice;
    private final int yellowDice;
    private final int redDice;

    Difficulty(String difficulty, int greenDice, int yellowDice, int redDice) {
        this.difficulty = difficulty;
        this.greenDice = greenDice;
        this.yellowDice = yellowDice;
        this.redDice = redDice;
    }

    public static Difficulty keyOf(String difficulty) {
        Optional<Difficulty> optionalDifficulty = Arrays.stream(Difficulty.values()).filter(el -> el.getDifficulty().equalsIgnoreCase(difficulty)).findFirst();
        return optionalDifficulty.isPresent() ? optionalDifficulty.get() : Difficulty.NORMAL;
    }

    public static ObservableList getDifficulties() {
        ObservableList<String> fxCollections = FXCollections.observableArrayList();

        for (Difficulty el : Difficulty.values()) fxCollections.add(el.getDifficulty());

        return fxCollections;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getGreenDice() {
        return greenDice;
    }

    public int getYellowDice() {
        return yellowDice;
    }

    public int getRedDice() {
        return redDice;
    }
}
