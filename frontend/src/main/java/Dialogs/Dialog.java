package Dialogs;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class Dialog {

    private Stage stage;
    private Scene scene;
    private BorderPane borderPane;

    public Dialog(int width, int height) {
        stage = new Stage();
        borderPane = new BorderPane();
        borderPane.getStylesheets().add("Style.css");
        scene = new Scene(borderPane, width, height);
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }
}
