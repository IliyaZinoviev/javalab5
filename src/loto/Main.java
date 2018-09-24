package loto;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        Game game = new Game();
        Pane root = new Pane(game.getCanvas());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Loto");
        stage.setWidth(400);
        stage.setHeight(425);
        stage.show();
        game.start();
    }
}
