package ch.supsi.handlers;

import ch.supsi.utility.SchedulerProperties;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

import java.util.Properties;
import java.util.ResourceBundle;

public class HelpHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(resourceBundle.getString("helpTitle.text"));
        Properties appInfo = SchedulerProperties.getAppInfo();
        alert.setContentText("Scheduler\nversion " + appInfo.getProperty("project.version") + "\nbuild " + appInfo.getProperty("project.build") + " (UTC)");
        alert.show();
    }

}
