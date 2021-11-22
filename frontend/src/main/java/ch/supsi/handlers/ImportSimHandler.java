package ch.supsi.handlers;

import ch.supsi.utility.DisplayAlert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class ImportSimHandler implements EventHandler<ActionEvent> {
    private DisplayAlert displayAlert;

    public ImportSimHandler(DisplayAlert displayAlert) {
        this.displayAlert = displayAlert;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Alert alert = displayAlert.getHelpHandler();
        alert.show();
    }
}
