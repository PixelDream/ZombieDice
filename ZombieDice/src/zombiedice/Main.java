package zombiedice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import zombiedice.model.Game;

public class Main extends Application {

    private static Stage stage;
    private static Game game;

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        Main.game = game;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;

        Parent root = FXMLLoader.load(getClass().getResource("/zombiedice/view/startScreen.fxml"));
        stage.setTitle("Zombie Dice");
        stage.setScene(new Scene(root, 1200, 800));
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.show();
    }
}
