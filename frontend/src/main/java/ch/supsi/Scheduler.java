package ch.supsi;

import ch.supsi.handlers.HelpHandler;
import ch.supsi.menu.MenuStructure;
import ch.supsi.menu.MenuView;
import ch.supsi.utility.DisplayAlert;
import controller.ControllerBackend;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Scheduler {
    private final VBox vboxMenu;
    private MenuStructure menuStructure;
    private HelpHandler helpHandler;
    private TabPaneNew tabPane;
    private MenuView menuView;
    private DisplayAlert displayAlert;
    private ControllerBackend controllerBackend = new ControllerBackend();

    public Scheduler() throws IOException {
        vboxMenu = new VBox();
        menuStructure = new MenuStructure();
        displayAlert = new DisplayAlert();
        menuView = new MenuView(displayAlert, controllerBackend);
        helpHandler = new HelpHandler(displayAlert);
        tabPane = new TabPaneNew(controllerBackend);
        setMenuBar();
    }

    private void setMenuBar() throws IOException {
        vboxMenu.getChildren().add(menuView.getMenuBar(this, tabPane));
        tabPane.setTabpane(this);
    }

    public VBox getVboxMenu() {
        return vboxMenu;
    }
}
