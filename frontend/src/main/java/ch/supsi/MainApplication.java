package ch.supsi;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;

public class MainApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Locale.setDefault(Locale.ITALIAN);
        stage.setTitle("Scheduler");
        Scheduler scheduler = new Scheduler();

        stage.setScene(new Scene(scheduler.getVboxMenu(), 800, 500));
        stage.show();
    }
}
