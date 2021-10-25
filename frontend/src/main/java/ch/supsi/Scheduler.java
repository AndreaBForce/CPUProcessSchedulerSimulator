package ch.supsi;

import ch.supsi.handlers.HelpHandler;
import ch.supsi.menu.MenuStructure;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Scheduler {
    private final VBox vboxMenu;
    private MenuStructure menuStructure;
    private HelpHandler helpHandler;
    private TabPaneNew tabPane;

    public Scheduler() {
        vboxMenu = new VBox();
        menuStructure = new MenuStructure();
        helpHandler = new HelpHandler();
        tabPane = new TabPaneNew();

        setMenuBar();
    }

    private void setMenuBar(){
        menuStructure.setMenuBar(this);
        menuStructure.getAboutItem().setOnAction(helpHandler);

        tabPane.setTabpane(this);
    }

    public VBox getVboxMenu() {
        return vboxMenu;
    }
}
