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

import java.io.File;
import java.io.IOException;

public class MenuView {

    private final ExitHandler exitHandler;
    private final HelpHandler helpHandler;
    private final MenuStructure menuStructure;
    private ControllerBackend controllerBackend;
    private TabPaneNew tabPaneNew;

    public MenuView(DisplayAlert displayAlert, ControllerBackend controllerBackend, TabPaneNew tabPaneNew) {
        this.controllerBackend = controllerBackend;
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
                controllerBackend.importSimulation();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        menuStructure.getExitItem().setOnAction(exitHandler);

        menuStructure.getMenuNew().setOnAction(actionEvent -> {
            CreateSimDialog createSimulation = new CreateSimDialog();
            createSimulation.getCreateStage().showAndWait();
            tabPaneNew.createNewSimulation(createSimulation.getName(), createSimulation.getAlgortihm(),createSimulation.isConfirm());
        });

        menuStructure.getMenuRandomSim().setOnAction(actionEvent -> {
            tabPaneNew.createRandomSimulation();
        });

        menuStructure.getAboutItem().setOnAction(helpHandler);

        menuStructure.getInfoAlgoItem().setOnAction(actionEvent -> {
            DisplayInfoDialog displayInfoDialog = new DisplayInfoDialog();

            displayInfoDialog.getDisplayStage().showAndWait();
        });

        return menuStructure.getMenuBar();
    }
}
