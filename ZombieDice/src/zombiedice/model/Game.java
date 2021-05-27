package zombiedice.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import zombiedice.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Game {

    private final List<Player> players;
    private final Difficulty difficulty;
    private DiceManager diceManager;

    public Game(Difficulty difficulty) {
        players = new ArrayList<>();
        diceManager = new DiceManager(difficulty);

        this.difficulty = difficulty;
    }

    /**
     * Get players (clone) and sort by score
     *
     * @return
     */
    public List<Player> getPlayersSort() {
        List<Player> playersClone = new ArrayList<>();
        playersClone.addAll(players);
        playersClone.sort(Comparator.comparingInt(Player::getScore).reversed());

        return playersClone;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void start() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/zombiedice/view/gameScreen.fxml"));
        Main.getStage().setScene(new Scene(root));
        Main.getStage().show();
    }

    /**
     * Create a new DiceManager before the next round
     */
    public void newRound() {
        diceManager = new DiceManager(difficulty);
    }

    public DiceManager getDiceManager() {
        return diceManager;
    }
}
