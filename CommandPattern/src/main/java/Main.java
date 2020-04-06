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
            primaryStage.setMinWidth(725);
            primaryStage.setMinHeight(300);
            primaryStage.setMaxWidth(725);
            primaryStage.setMaxHeight(1000);
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Command Editor");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
