package ch.supsi;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Locale;

public class MainApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Locale.setDefault(Locale.ITALIAN);

        stage.setTitle("Scheduler");
        stage.setScene(new Scene(new GridPane(), 800, 500));

        stage.show();
    }
}
