package zombiedice.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import zombiedice.Main;
import zombiedice.model.Game;
import zombiedice.model.Player;
import zombiedice.model.exeption.NoMoreDiceException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    public static int currentBrain = 0, currentFire = 0;
    boolean isPlaying = false, isFinish = false;
    int currentPlayerID = 0;
    @FXML
    VBox players;
    @FXML
    Text player, greenCount, redCount, yellowCount, brainCount, fireCount;
    @FXML
    ImageView dice1, dice2, dice3, brain, fire;
    @FXML
    Button launch, next, leave;
    @FXML
    AnchorPane brainFrame, fireFrame;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player.setText(Main.getGame().getPlayers().get(0).getName());
        updateScoreBoard();
    }

    /**
     * Update scoreBoard for each players
     */
    public void updateScoreBoard() {
        Game game = Main.getGame();

        players.getChildren().clear();

        for (Player player : game.getPlayersSort()) {
            AnchorPane snippet = null;

            try {
                snippet = FXMLLoader.load(getClass().getResource("/zombiedice/view/snippets/scoreSnippet.fxml"));
            } catch (IOException e) {
                leave();
            }


            Text name = (Text) snippet.getChildren().get(0);
            name.setText(player.getName());

            Text score = (Text) snippet.getChildren().get(1);
            score.setText(player.showScore());

            players.getChildren().add(snippet);
        }

        greenCount.setText(game.getDiceManager().getGreenDice());
        yellowCount.setText(game.getDiceManager().getYellowDice());
        redCount.setText(game.getDiceManager().getRedDice());

        brainCount.setText(String.valueOf(currentBrain));
        fireCount.setText(String.valueOf(currentFire));
    }

    @FXML
    private void launch() {

        if (!isPlaying) showCount();

        try {
            dice1.setImage(new Image(String.valueOf(getClass().getResource(Main.getGame().getDiceManager().getRandomDice()))));
            dice2.setImage(new Image(String.valueOf(getClass().getResource(Main.getGame().getDiceManager().getRandomDice()))));
            dice3.setImage(new Image(String.valueOf(getClass().getResource(Main.getGame().getDiceManager().getRandomDice()))));
        } catch (NoMoreDiceException e) {
            System.out.println("Finish round");
            next();
        }

        if (currentFire >= 3) {
            currentBrain = 0;
            next();
        }

        updateScoreBoard();
    }

    @FXML
    private void next() {
        Main.getGame().getPlayers().get(currentPlayerID).addScore(currentBrain);

        if (currentPlayerID++ >= Main.getGame().getPlayers().size() - 1) {
            Player p = Main.getGame().getPlayersSort().get(0);
            if (p.getScore() >= 13) {
                player.setText("Terminé !  " + p.getName() + " a gagné !");
                launch.setDisable(true);
                next.setDisable(true);
                leave.setVisible(true);

                isFinish = true;
            }
            currentPlayerID = 0;
        }

        if (!isFinish) player.setText(Main.getGame().getPlayers().get(currentPlayerID).getName());

        resetGame();
        updateScoreBoard();
    }

    @FXML
    private void leave() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/zombiedice/view/startScreen.fxml"));
            Main.getStage().setScene(new Scene(root));
            Main.getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show all elements
     */
    private void showCount() {
        brain.setVisible(true);
        brainFrame.setVisible(true);
        fire.setVisible(true);
        fireFrame.setVisible(true);
        dice1.setVisible(true);
        dice2.setVisible(true);
        dice3.setVisible(true);
    }

    /**
     * Reset values and hide all elements
     */
    private void resetGame() {
        isPlaying = false;
        brain.setVisible(false);
        brainFrame.setVisible(false);
        fire.setVisible(false);
        fireFrame.setVisible(false);
        dice1.setVisible(false);
        dice2.setVisible(false);
        dice3.setVisible(false);
        brainCount.setText("0");
        fireCount.setText("0");
        currentBrain = 0;
        currentFire = 0;
        Main.getGame().newRound();
    }

}
