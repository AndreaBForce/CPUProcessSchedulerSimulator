package ch.supsi;

import ch.supsi.handlers.HelpHandler;
import ch.supsi.menu.MenuStructure;
import javafx.scene.layout.Pane;

public class Scheduler {
    private final Pane pane;
    private MenuStructure menuStructure;
    private HelpHandler helpHandler;


    public Scheduler() {
        pane = new Pane();
        menuStructure = new MenuStructure();
        helpHandler = new HelpHandler();
        setMenuBar();
    }

    private void setMenuBar(){
        menuStructure.setMenuBar(this);
        menuStructure.getAboutItem().setOnAction(helpHandler);
    }

    public Pane getPane() {
        return pane;
    }
}
