import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Simple Editor");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
