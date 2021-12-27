package ch.supsi.menu;

import Dialogs.CreateSimDialog;
import Dialogs.DisplayInfoDialog;
import ch.supsi.*;
import ch.supsi.handlers.ExitHandler;
import ch.supsi.handlers.HelpHandler;
import ch.supsi.utility.DisplayAlert;
import controller.ControllerBackend;
import javafx.scene.control.MenuBar;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import utility.Simulation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuView {

    private final ExitHandler exitHandler;
    private final HelpHandler helpHandler;
    private final MenuStructure menuStructure;
    ControllerBackend controllerBackend = new ControllerBackend();

    public MenuView(DisplayAlert displayAlert) {
        this.exitHandler = new ExitHandler(displayAlert);
        this.helpHandler = new HelpHandler(displayAlert);
        this.menuStructure = new MenuStructure();
    }

    public MenuBar getMenuBar(Scheduler scheduler, TabPaneNew tabPaneNew) throws IOException {
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

       menuStructure.getMenuExportSim().setOnAction(actionEvent -> {
           try {
               controllerBackend.exportSimulation(new Simulation("ciao", "lfs", new ArrayList<>()));
           } catch (IOException e) {
               e.printStackTrace();
           }
       });

        menuStructure.getMenuImportSim().setOnAction(actionEvent -> {
            try {
                controllerBackend.importSimulation();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        menuStructure.getExitItem().setOnAction(exitHandler);

        //Menu che mostra il popup per creare la nuova simulazione
        menuStructure.getMenuNew().setOnAction(actionEvent -> {
            TextInputDialog td = new TextInputDialog("");
            td.setHeaderText(resourceBundle.getString("textInputDialog.text"));
            td.showAndWait();
            tabPaneNew.addTab(new TabView(td.getEditor().getText()));
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
