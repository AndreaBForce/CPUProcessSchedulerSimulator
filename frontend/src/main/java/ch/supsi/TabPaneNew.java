package ch.supsi;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;


public class TabPaneNew {
    private TabPane tabpane;
    private ProcessChartView processChartView;

    public TabPaneNew() {
//        Platform.setImplicitExit(false);
        tabpane = new TabPane();

        Tab plus = new Tab("+");
        plus.setClosable(false);

        //Parte da spostare che permette la creazione di un nuovo tab
        plus.setOnSelectionChanged (e ->{
            if(plus.isSelected()){
                TabView aggiunta = new TabView("ProcessoN", "ciao");
                ProcessListView lista = new ProcessListView();
                processChartView = new ProcessChartView();
                processChartView.testChart();
                VBox vBox = new VBox();
                vBox.setAlignment(Pos.BOTTOM_CENTER);
                vBox.setStyle("-fx-background-color: #f8fce5");
                VBox.setVgrow(lista.getContainer(), Priority.ALWAYS);
                Region space = new Region();
                space.setStyle("-fx-background-color: #ffd500");
                space.setMinHeight(50);
                vBox.getChildren().addAll(lista.getContainer(), space,processChartView.getChart());
                aggiunta.getTab().setContent(vBox);

                addTab(aggiunta);
            }
        }
        );

        VBox.setVgrow(tabpane, Priority.ALWAYS);
        tabpane.setStyle("-fx-background-color: yellow");
        //Evento che gestisce i soci
        tabpane.getTabs().add(plus);
    }

    public void addTab(TabView tab){
        tabpane.getTabs().add(tab.getTab());
    }

    public TabPane getTabpane() {
        return tabpane;
    }

    public ProcessChartView getProcessChartView() {
        return processChartView;
    }

    public void setTabpane(Scheduler schedulder) {
        schedulder.getVboxMenu().getChildren().add(getTabpane());
    }
}