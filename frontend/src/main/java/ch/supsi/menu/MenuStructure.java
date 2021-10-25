package ch.supsi.menu;

import ch.supsi.Scheduler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.util.ResourceBundle;

public class MenuStructure {

    private MenuItem aboutItem;

    public void setMenuBar(Scheduler schedulder){
        MenuBar menuBar = new MenuBar();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");
        Menu menuFile = new Menu(resourceBundle.getString("menuFile.text"));
        MenuItem exitItem = new MenuItem(resourceBundle.getString("menuExit.text"));
        Menu menuEdit = new Menu(resourceBundle.getString("menuEdit.text"));
        MenuItem menuNew = new MenuItem(resourceBundle.getString("menuNewEvent.text"));
        Menu menuHelp = new Menu(resourceBundle.getString("menuHelp.text"));
        aboutItem = new MenuItem(resourceBundle.getString("menuAbout.text"));

        menuBar.getMenus().add(menuFile);
        menuBar.getMenus().add(menuEdit);
        menuBar.getMenus().add(menuHelp);

        menuFile.getItems().add(exitItem);
        menuEdit.getItems().add(menuNew);
        menuHelp.getItems().add(aboutItem);

        menuBar.getStyleClass().add("menuBar");
        menuBar.prefWidthProperty().bind(schedulder.getVboxMenu().widthProperty());
        schedulder.getVboxMenu().getChildren().add(menuBar);
    }

    public MenuItem getAboutItem() {
        return aboutItem;
    }
}
