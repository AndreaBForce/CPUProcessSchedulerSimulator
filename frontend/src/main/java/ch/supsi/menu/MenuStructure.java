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
    private MenuItem menuNew;
    private MenuItem menuExportGraph;
    private MenuItem menuImportSim;
    private MenuItem menuExportSim;
    private Menu menuHelp;

    private MenuItem aboutItem;
    private MenuItem infoAlgoItem;

    private MenuBar menuBar = new MenuBar();

    public void setMenuBar(Scheduler scheduler){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");
        menuFile = new Menu(resourceBundle.getString("menuFile.text"));
        exitItem = new MenuItem(resourceBundle.getString("menuExit.text"));
        menuEdit = new Menu(resourceBundle.getString("menuEdit.text"));
        menuNew = new MenuItem(resourceBundle.getString("menuNewSim.text"));
        menuHelp = new Menu(resourceBundle.getString("menuHelp.text"));

        aboutItem = new MenuItem(resourceBundle.getString("menuAbout.text"));
        infoAlgoItem = new MenuItem(resourceBundle.getString("menuInfoAlgorithm.text"));

        menuExportGraph = new MenuItem(resourceBundle.getString("menuExportGraph.text"));
        menuImportSim = new MenuItem(resourceBundle.getString("menuImportGraph.text"));
        menuExportSim = new MenuItem(resourceBundle.getString("menuExportSim.text"));

        menuBar.getMenus().add(menuFile);
        menuBar.getMenus().add(menuEdit);
        menuBar.getMenus().add(menuHelp);

        menuFile.getItems().add(exitItem);
        menuFile.getItems().add(menuExportGraph);
        menuFile.getItems().add(menuImportSim);
        menuFile.getItems().add(menuExportSim);
        menuEdit.getItems().add(menuNew);

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

    public MenuItem getMenuExportGraph() {
        return menuExportGraph;
    }

    public MenuItem getMenuExportSim() {
        return menuExportSim;
    }

    public MenuItem getMenuImportSim() {
        return menuImportSim;
    }
}
