package ch.supsi;

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
                //Se e' true si chiude la tab
                //Se e' false l'evento non si chiude
                event.consume();
            }
        });
    }

    public Tab getTab() {
        return tab;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }

}
