package ch.supsi.handlers;

import ch.supsi.utility.DisplayAlert;
import ch.supsi.utility.SchedulerProperties;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

import java.util.Properties;
import java.util.ResourceBundle;

public class HelpHandler implements EventHandler<ActionEvent> {
    private DisplayAlert displayAlert;

    public HelpHandler(DisplayAlert displayAlert) {
        this.displayAlert = displayAlert;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Alert alert = displayAlert.getHelpHandler();
        alert.show();
    }
}
