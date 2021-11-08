package ch.supsi;

import ch.supsi.handlers.SaveSimulationHandler;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;

public class TabView {
    private Tab tab;
    private ExitDialog exit;

    public TabView(String nome) {
        this.tab = new Tab(nome);

        tab.setOnCloseRequest(event -> {
            exit = new ExitDialog();

            exit.getExitStage().showAndWait();


                if(!exit.isEnd()) {

                    event.consume();
                }
            }
        );
//        tab.setOnCloseRequest(event -> new SaveSimulationHandler());
    }

    public Tab getTab() {
        return tab;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }

}
