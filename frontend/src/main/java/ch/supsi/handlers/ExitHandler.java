package ch.supsi.handlers;

import ch.supsi.utility.DisplayAlert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class ExitHandler implements EventHandler<ActionEvent> {

    private final DisplayAlert displayAlert;

    public ExitHandler(DisplayAlert displayAlert) {
        this.displayAlert = displayAlert;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Alert alert = displayAlert.getExitHandler();
        Optional<ButtonType> res = alert.showAndWait();
        if (res.get().equals(ButtonType.OK)) {
            System.exit(1);
        }
    }
}
