package ch.supsi.menu;

import Dialogs.SimulationDialog;
import Dialogs.DisplayInfoDialog;
import ch.supsi.*;
import ch.supsi.controller.Mediator;
import ch.supsi.handlers.ExitHandler;
import ch.supsi.handlers.HelpHandler;
import ch.supsi.utility.DisplayAlert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;
import utility.Simulation;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

public class MenuView {

    private final ExitHandler exitHandler;
    private final HelpHandler helpHandler;
    private final MenuStructure menuStructure;
    private Mediator mediator;
    private TabPaneNew tabPaneNew;

    public MenuView(DisplayAlert displayAlert, Mediator mediator, TabPaneNew tabPaneNew) {
        this.mediator = mediator;
        this.tabPaneNew = tabPaneNew;
        this.exitHandler = new ExitHandler(displayAlert);
        this.helpHandler = new HelpHandler(displayAlert);
        this.menuStructure = new MenuStructure();
    }

    public MenuBar getMenuBar(Scheduler scheduler, TabPaneNew tabPaneNew) {
        menuStructure.setMenuBar(scheduler);

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("/home/matteo"));

        menuStructure.getMenuImportSim().setOnAction(actionEvent -> {
            try {
                TextInputDialog textInputDialog = new TextInputDialog();
                textInputDialog.setContentText("Enter name of file: ");
                String fileName = textInputDialog.showAndWait().get();

                if (!fileName.equals("")) {
                    Simulation simulation = mediator.importSimulation(fileName);
                    if (simulation != null)
                        tabPaneNew.importSimulation(simulation);
                }
            } catch (IOException | NoSuchElementException e) {
            }
        });

        menuStructure.getExitItem().setOnAction(exitHandler);

        menuStructure.getMenuNew().setOnAction(actionEvent -> {
            SimulationDialog createSimulation = new SimulationDialog();
            createSimulation.getStage().showAndWait();
            tabPaneNew.createNewSimulation(createSimulation.getName(), createSimulation.getAlgorithm(), createSimulation.isConfirm());
        });

        menuStructure.getMenuRandomSim().setOnAction(actionEvent -> {
            tabPaneNew.createRandomSimulation();
        });

        menuStructure.getAboutItem().setOnAction(helpHandler);

        menuStructure.getInfoAlgoItem().setOnAction(actionEvent -> {
            DisplayInfoDialog displayInfoDialog = new DisplayInfoDialog();

            displayInfoDialog.getStage().showAndWait();
        });

        return menuStructure.getMenuBar();
    }
}
