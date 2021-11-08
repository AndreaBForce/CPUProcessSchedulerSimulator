package ch.supsi.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

import java.util.ResourceBundle;

public class SaveSimulationHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(resourceBundle.getString("helpTitle.text"));
        alert.setContentText("vuoi salvare la simulazione?");
    }
}
