package ch.supsi;

import ch.supsi.controller.Mediator;
import ch.supsi.handlers.HelpHandler;
import ch.supsi.menu.MenuStructure;
import ch.supsi.menu.MenuView;
import ch.supsi.utility.DisplayAlert;
import controller.ControllerBackend;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Scheduler {
    private final VBox vboxMenu;
    private Mediator mediator;
    private MenuStructure menuStructure;
    private HelpHandler helpHandler;
    private TabPaneNew tabPane;
    private MenuView menuView;
    private DisplayAlert displayAlert;
    private ControllerBackend controllerBackend = new ControllerBackend();

    public Scheduler() {
        vboxMenu = new VBox();
        mediator = new Mediator();
        menuStructure = new MenuStructure();
        displayAlert = new DisplayAlert();
        helpHandler = new HelpHandler(displayAlert);
        tabPane = new TabPaneNew(this, mediator);
        menuView = new MenuView(displayAlert, mediator, tabPane);
        setMenuBar();
    }

    private void setMenuBar() {
        vboxMenu.getChildren().add(menuView.getMenuBar(this, tabPane));
        tabPane.setTabpane(this);
    }

    public VBox getVboxMenu() {
        return vboxMenu;
    }
}
