package ch.supsi.menu;

import Dialogs.CreateSimDialog;
import Dialogs.DisplayInfoDialog;
import ch.supsi.*;
import ch.supsi.handlers.ExitHandler;
import ch.supsi.handlers.HelpHandler;
import ch.supsi.utility.DisplayAlert;
import javafx.scene.control.MenuBar;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ResourceBundle;

public class MenuView {

    private final ExitHandler exitHandler;
    private final HelpHandler helpHandler;
    private final MenuStructure menuStructure;

    public MenuView(DisplayAlert displayAlert) {
        this.exitHandler = new ExitHandler(displayAlert);
        this.helpHandler = new HelpHandler(displayAlert);
        this.menuStructure = new MenuStructure();
    }

    public MenuBar getMenuBar(Scheduler scheduler, TabPaneNew tabPaneNew) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");
        menuStructure.setMenuBar(scheduler);

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("/home/matteo"));

        menuStructure.getMenuExportGraph().setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extFilterPng = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
            FileChooser.ExtensionFilter extFilterJpeg = new FileChooser.ExtensionFilter("Jpeg files (*.jpeg)", "*.jpeg");
            fileChooser.getExtensionFilters().add(extFilterPng);
            fileChooser.getExtensionFilters().add(extFilterJpeg);

            File file = fileChooser.showSaveDialog(scheduler.getVboxMenu().getScene().getWindow());
            if (file != null) {
                tabPaneNew.getProcessChartView().exportImage(file);
            }
        });

//        menuStructure.getMenuExportSim().setOnAction();

//        menuStructure.getMenuImportSim().setOnAction();

        menuStructure.getExitItem().setOnAction(exitHandler);

        //Menu che mostra il popup per creare la nuova simulazione
        menuStructure.getMenuNew().setOnAction(actionEvent -> {

            CreateSimDialog createSimulation = new CreateSimDialog();

            createSimulation.getCreateStage().showAndWait();

            tabPaneNew.createNewSimulation(createSimulation.getName(), createSimulation.getAlgortihm(),createSimulation.isConfirm());

        });


        menuStructure.getAboutItem().setOnAction(helpHandler);

        //Action che mostra il popup per mostrare la descrizione di come funziona un processo
        menuStructure.getInfoAlgoItem().setOnAction(actionEvent -> {
            DisplayInfoDialog displayInfoDialog = new DisplayInfoDialog();

            displayInfoDialog.getDisplayStage().showAndWait();
        });

        return menuStructure.getMenuBar();
    }
}
