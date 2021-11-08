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

//        tab.setOnCloseRequest(new EventHandler<Event>() {
//            @Override
//            public void handle(Event event) {
//                exit = new ExitDialog();
//
//                exit.getExitStage().showAndWait();
//
//
//                if(exit.isEnd()) {
//                    //Se e' true si chiude la tab
//                }else {
//                    //Se e' false l'evento non si chiude
//                    event.consume();
//                }
//            }
//        });
        tab.setOnCloseRequest(event -> new SaveSimulationHandler());
    }

    public Tab getTab() {
        return tab;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }

}
