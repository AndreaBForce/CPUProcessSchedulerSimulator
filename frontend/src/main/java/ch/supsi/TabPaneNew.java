package ch.supsi;

import Dialogs.CreateSimDialog;
import ch.supsi.controller.SerializerJSON;
import ch.supsi.utility.Simulation;
import controller.ControllerBackend;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.List;

public class TabPaneNew {
    private TabPane tabpane;
    private ProcessChartView processChartView;
    private Simulation simulation;
    private Button startButton;
    private ControllerBackend controllerBackend;

    public TabPaneNew(ControllerBackend controllerBackend) {
        this.controllerBackend = controllerBackend;
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
            TabView newTab = new TabView(nameSimulation, nameAlgorithm);
            ProcessListView list = new ProcessListView();
            simulation = new Simulation(nameSimulation, nameAlgorithm, list.getProcessList());
            processChartView = new ProcessChartView();
            processChartView.testChart();
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.BOTTOM_CENTER);
            vBox.setStyle("-fx-background-color: #f8fce5");
            VBox.setVgrow(list.getContainer(), Priority.ALWAYS);

            startButton = new Button("start");
            HBox hBox = new HBox();
            hBox.setStyle("-fx-background-color: #ffd500");
            hBox.setMinHeight(50);
            hBox.getChildren().add(startButton);
            hBox.setAlignment(Pos.CENTER);

            vBox.getChildren().addAll(list.getContainer(), hBox, processChartView.getChart());
            newTab.getTab().setContent(vBox);
            addTab(newTab);
        }

        startButton.setOnMouseClicked(mouseEvent -> {
            SerializerJSON serializerJSON = new SerializerJSON();
            try {
                serializerJSON.serialize(simulation.getProcessList());
                controllerBackend.fcfsScheduler();
                List<Process> processList = serializerJSON.deserialize();

                for (Process process : processList) {
                    processChartView.add(process, (int) process.getBurstTime(), "yellow");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public Button getStartButton() {
        return startButton;
    }
}