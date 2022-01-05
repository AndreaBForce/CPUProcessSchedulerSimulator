package ch.supsi;

import Dialogs.CreateSimDialog;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;


public class TabPaneNew {
    private TabPane tabpane;
    private ProcessChartView processChartView;

    public TabPaneNew() {
        tabpane = new TabPane();
        CreateSimDialog createSimulation = new CreateSimDialog();
        Tab plus = new Tab("+");
        plus.setClosable(false);

        //Parte da spostare che permette la creazione di un nuovo tab
        plus.setOnSelectionChanged (e ->{

            if(plus.isSelected()){


                if(createSimulation.isFirst()){
                    createNewSimulation("Simulation tab", "FIFO", true);
                }else {
                    createSimulation.getCreateStage().showAndWait();
                    createNewSimulation(createSimulation.getName(), createSimulation.getAlgortihm(), createSimulation.isConfirm());
                }
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

    public void createNewSimulation(String nameSimulation,String nameAlgorithm,boolean confirmed){

        if(confirmed) {
            TabView aggiunta = new TabView(nameSimulation, nameAlgorithm);
            ProcessListView lista = new ProcessListView();
            lista.setAlgortihm(nameAlgorithm);

            processChartView = new ProcessChartView();
            processChartView.testChart();
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.BOTTOM_CENTER);
            vBox.setStyle("-fx-background-color: #f8fce5");
            VBox.setVgrow(lista.getContainer(), Priority.ALWAYS);
            Region space = new Region();
            space.setStyle("-fx-background-color: #ffd500");
            space.setMinHeight(50);
            vBox.getChildren().addAll(lista.getContainer(), space, processChartView.getChart());
            aggiunta.getTab().setContent(vBox);
            addTab(aggiunta);
        }
    }

}