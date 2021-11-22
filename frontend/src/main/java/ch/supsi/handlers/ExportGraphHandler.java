package ch.supsi.handlers;

import ch.supsi.Scheduler;
import ch.supsi.utility.DisplayAlert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;

public class ExportGraphHandler implements EventHandler<ActionEvent> {
    private FileChooser fileChooser;
    private Scheduler scheduler;

    public ExportGraphHandler(Scheduler scheduler) {
        this.scheduler = scheduler;
        fileChooser = new FileChooser();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        fileChooser.showSaveDialog(scheduler.getVboxMenu().getScene().getWindow());
    }
}
