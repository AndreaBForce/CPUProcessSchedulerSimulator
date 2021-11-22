package ch.supsi.menu;

import ch.supsi.Scheduler;
import ch.supsi.TabPaneNew;
import ch.supsi.TabView;
import ch.supsi.handlers.ExitHandler;
import ch.supsi.handlers.ExportGraphHandler;
import ch.supsi.handlers.HelpHandler;
import ch.supsi.utility.DisplayAlert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextInputDialog;

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

        menuStructure.getMenuExportGraph().setOnAction(new ExportGraphHandler(scheduler));

//        menuStructure.getMenuExportSim().setOnAction();

//        menuStructure.getMenuImportSim().setOnAction();

        menuStructure.getExitItem().setOnAction(exitHandler);

        menuStructure.getMenuNew().setOnAction(actionEvent -> {
            TextInputDialog td = new TextInputDialog("");
            td.setHeaderText(resourceBundle.getString("textInputDialog.text"));
            td.showAndWait();
            tabPaneNew.addTab(new TabView(td.getEditor().getText()));
        });

        menuStructure.getAboutItem().setOnAction(helpHandler);

        return menuStructure.getMenuBar();
    }
}
