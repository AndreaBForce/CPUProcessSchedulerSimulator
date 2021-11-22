package ch.supsi.utility;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.util.Properties;
import java.util.ResourceBundle;

public class DisplayAlert {
    private final Alert errorHandler = new Alert(Alert.AlertType.ERROR);
    private final Alert exitHandler = new Alert(Alert.AlertType.CONFIRMATION);
    private final Alert helpHandler = new Alert(Alert.AlertType.INFORMATION);
    private final FileChooser fileChooser = new FileChooser();
    private final SchedulerProperties schedulerProperties = new SchedulerProperties();

    public Alert getErrorHandler() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");
        errorHandler.setTitle(resourceBundle.getString("errorTitle.text"));
        errorHandler.setHeaderText(resourceBundle.getString("errorHeader.text"));
        return errorHandler;
    }

    public Alert getExitHandler() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");
        exitHandler.setTitle(resourceBundle.getString("exitTitle.text"));
        exitHandler.setHeaderText(resourceBundle.getString("exitHeader.text"));
        exitHandler.setContentText(resourceBundle.getString("exitContent.text"));
        return exitHandler;
    }

    public Alert getHelpHandler() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");
        helpHandler.setTitle(resourceBundle.getString("helpTitle.text"));
        Properties appInfo = schedulerProperties.getAppInfo();
        helpHandler.setContentText("Calendar app\nversion " + appInfo.getProperty("project.version") + "\nbuild " + appInfo.getProperty("project.build") + " (UTC)");
        return helpHandler;
    }

    public void getExportHandler() {

    }
}
