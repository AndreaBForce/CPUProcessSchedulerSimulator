package ch.supsi;

import ch.supsi.handlers.SaveSimulationHandler;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class TabView {
    private Tab tab;
    private ExitDialog exit;
    private String nameSimulazion;
    private String nameAlgorithm;

    public TabView(String nome,String nameAlgorithm) {
        this.nameSimulazion = nome;
        this.nameAlgorithm = nameAlgorithm;
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
