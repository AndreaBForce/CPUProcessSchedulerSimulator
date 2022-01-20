package ch.supsi.menu;

import ch.supsi.Scheduler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.util.ResourceBundle;

public class MenuStructure {

    private Menu menuFile;
    private MenuItem exitItem;
    private Menu menuEdit;
    private MenuItem menuRandomSim;
    private MenuItem menuNew;
    private MenuItem menuImportSim;
    private Menu menuHelp;

    private MenuItem aboutItem;
    private MenuItem infoAlgoItem;

    private MenuBar menuBar = new MenuBar();

    public void setMenuBar(Scheduler scheduler) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");
        menuFile = new Menu(resourceBundle.getString("menuFile.text"));
        menuFile.setId("fileMenu");
        exitItem = new MenuItem(resourceBundle.getString("menuExit.text"));
        exitItem.setId("exitItem");
        menuEdit = new Menu(resourceBundle.getString("menuEdit.text"));
        menuEdit.setId("editMenu");
        menuRandomSim = new MenuItem(resourceBundle.getString("menuRandomSim.text"));
        menuRandomSim.setId("randomSimItem");
        menuNew = new MenuItem(resourceBundle.getString("menuNewSim.text"));
        menuNew.setId("newSimItem");
        menuHelp = new Menu(resourceBundle.getString("menuHelp.text"));
        menuHelp.setId("helpMenu");

        aboutItem = new MenuItem(resourceBundle.getString("menuAbout.text"));
        aboutItem.setId("aboutItem");
        infoAlgoItem = new MenuItem(resourceBundle.getString("menuInfoAlgorithm.text"));
        infoAlgoItem.setId("infoAlgoItem");

        menuImportSim = new MenuItem(resourceBundle.getString("menuImportGraph.text"));
        menuImportSim.setId("importSimItem");

        menuBar.getMenus().add(menuFile);
        menuBar.getMenus().add(menuEdit);
        menuBar.getMenus().add(menuHelp);

        menuFile.getItems().add(exitItem);
        menuFile.getItems().add(menuImportSim);
        menuEdit.getItems().add(menuNew);
        menuEdit.getItems().add(menuRandomSim);

        menuHelp.getItems().add(aboutItem);
        menuHelp.getItems().add(infoAlgoItem);


        menuBar.getStyleClass().add("menuBar");
        menuBar.prefWidthProperty().bind(scheduler.getVboxMenu().widthProperty());
        menuBar.setMinWidth(400);
    }

    public MenuItem getMenuNew() {
        return menuNew;
    }

    public MenuItem getExitItem() {
        return exitItem;
    }

    public MenuItem getAboutItem() {
        return aboutItem;
    }

    public MenuItem getInfoAlgoItem() {
        return infoAlgoItem;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public MenuItem getMenuRandomSim() {
        return menuRandomSim;
    }

    public MenuItem getMenuImportSim() {
        return menuImportSim;
    }
}
