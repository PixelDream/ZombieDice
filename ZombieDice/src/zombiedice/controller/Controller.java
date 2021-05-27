package zombiedice.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import zombiedice.Main;
import zombiedice.model.Difficulty;
import zombiedice.model.Game;
import zombiedice.model.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private boolean isNbPlayer = false, isDifficulty = false;

    @FXML
    private VBox players;
    @FXML
    private ChoiceBox nbPlayers, difficulty;
    @FXML
    private Button play;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 2; i <= 10; i++) nbPlayers.getItems().add(i);
        difficulty.getItems().setAll(Difficulty.getDifficulties());

        difficulty.setOnAction(actionEvent -> {
            isDifficulty = true;
            checkValidate();
        });
    }

    private void checkValidate() {
        if (isDifficulty && isNbPlayer) play.setVisible(true);
    }

    @FXML
    private void play() throws IOException {
        Difficulty difficultyOf = Difficulty.keyOf(difficulty.getValue().toString());
        Main.setGame(new Game(difficultyOf));

        players.getChildren().forEach(node -> {
            AnchorPane anchorPane = (AnchorPane) node;
            TextField textField = (TextField) anchorPane.getChildren().get(1);

            Main.getGame().getPlayers().add(new Player(textField.getText()));
        });

        Main.getGame().start();
    }

    @FXML
    private void nbPlayers() throws IOException {
        int playersSize = players.getChildren().size();
        int choicePlayerSize = Integer.parseInt(nbPlayers.getValue().toString());

        if (playersSize == choicePlayerSize) return;

        if (choicePlayerSize < playersSize) {
            players.getChildren().remove(choicePlayerSize, playersSize);
        } else {
            int diff = choicePlayerSize - playersSize;

            for (int i = 0; i < diff; i++) {
                AnchorPane snippet = FXMLLoader.load(getClass().getResource("/zombiedice/view/snippets/playerSnippet.fxml"));

                Text text = (Text) snippet.getChildren().get(0);
                text.setText("Joueur " + (i + 1));

                TextField textField = (TextField) snippet.getChildren().get(1);
                textField.setText("Joueur " + (i + 1));

                players.getChildren().add(snippet);
            }
        }

        isNbPlayer = true;
        checkValidate();
    }
}
