package ch.supsi;

import Dialogs.CreateSimDialog;
import ch.supsi.controller.SerializerJSON;
import ch.supsi.utility.Simulation;
import controller.ControllerBackend;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import utility.SimulationBackend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class TabPaneNew {
    private TabPane tabpane;
    private List<TabView> tabViewList = new ArrayList<>();
    private Button startButton = new Button("start");
    ;
    private ControllerBackend controllerBackend;
    private Scheduler scheduler;
    private SerializerJSON serializerJSON = new SerializerJSON();

    public TabPaneNew(ControllerBackend controllerBackend, Scheduler scheduler) {
        this.controllerBackend = controllerBackend;
        this.scheduler = scheduler;
        tabpane = new TabPane();
        CreateSimDialog createSimulation = new CreateSimDialog();
        Tab plus = new Tab("+");
        plus.setClosable(false);

        plus.setOnSelectionChanged(e -> {
                    if (plus.isSelected()) {
                        if (createSimulation.isFirst()) {
                            createNewSimulation("Simulation tab", "FIFO", true);
                        } else {
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

    public void addTab(TabView tab) {
        tabpane.getTabs().add(tab.getTab());
    }

    public TabPane getTabpane() {
        return tabpane;
    }

    public void setTabpane(Scheduler schedulder) {
        schedulder.getVboxMenu().getChildren().add(getTabpane());
    }

    public void createRandomSimulation() {
        ProcessListView list = new ProcessListView();
        String[] algorithms = {"RMA", "Lottery", "FCFS", "EDF", "SJF", "Round Robin"};
        int algorithm = (int) (Math.random() * 6);
        int numProcess = (int) (Math.random() * 5) + 1;
        list.setAlgortihm(algorithms[algorithm]);
        for (int i = 0; i < numProcess; i++) {
            list.add(new Process("p" + i, (float) (Math.random() * 10.9) + 0.1f, (float) Math.random(), (int) (Math.random() * 11), Color.color(Math.random(), Math.random(), Math.random())));
        }

        Button exportGraph = new Button("Export graph");
        exportGraph.setDisable(true);
        Button exportSim = new Button("Export simulation");
        exportSim.setDisable(true);
        TabView newTab = new TabView("random", algorithms[algorithm]);
        tabViewList.add(newTab);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.BOTTOM_CENTER);
        vBox.setStyle("-fx-background-color: #f8fce5");
        VBox.setVgrow(list.getContainer(), Priority.ALWAYS);

        vBox.getChildren().addAll(list.getContainer(), getHbox(exportGraph, exportSim, algorithms[algorithm]), newTab.getProcessChartView().getChart());
        newTab.getTab().setContent(vBox);
        addTab(newTab);

        exportGraph.setOnMouseClicked(mouseEvent -> {
            exportGraph();
        });

        exportSim.setOnMouseClicked(mouseEvent -> {
            exportSim();
        });

        startButton.setOnMouseClicked(mouseEvent -> {
            exportGraph.setDisable(false);
            exportSim.setDisable(false);
            TabView select = getSelectedTab();
            select.getProcessChartView().getProcessList().getData().clear();
            select.setSimulation(new Simulation("random", algorithms[algorithm], list.getProcessList()));
            serializeProcess(algorithms[algorithm], select, list);
        });
    }

    public void createNewSimulation(String nameSimulation, String nameAlgorithm, boolean confirmed) {
        ProcessListView list = new ProcessListView();
        list.setAlgortihm(nameAlgorithm);
        Button exportGraph = new Button("Export graph");
        exportGraph.setDisable(true);
        Button exportSim = new Button("Export simulation");
        exportSim.setDisable(true);
        TabView newTab = new TabView(nameSimulation, nameAlgorithm);
        tabViewList.add(newTab);
        if (confirmed) {
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.BOTTOM_CENTER);
            vBox.setStyle("-fx-background-color: #f8fce5");
            VBox.setVgrow(list.getContainer(), Priority.ALWAYS);

            vBox.getChildren().addAll(list.getContainer(), getHbox(exportGraph, exportSim, nameAlgorithm), newTab.getProcessChartView().getChart());
            newTab.getTab().setContent(vBox);
            addTab(newTab);
        }

        exportGraph.setOnMouseClicked(mouseEvent -> {
            exportGraph();
        });

        exportSim.setOnMouseClicked(mouseEvent -> {
            exportSim();
        });

        startButton.setOnMouseClicked(mouseEvent -> {
            exportGraph.setDisable(false);
            exportSim.setDisable(false);
            TabView select = getSelectedTab();
            select.getProcessChartView().getProcessList().getData().clear();
            select.setSimulation(new Simulation(nameSimulation, nameAlgorithm, list.getProcessList()));
            serializeProcess(nameAlgorithm, select, list);
        });
    }

    public Color getColor(String name, List<Process> processList) {
        for (Process process : processList)
            if (process.getName().equals(name))
                return process.getColor();
        if (name.equals("SP")) {
            return Color.WHITE;
        } else
            return Color.RED;
    }

    public void exportGraph() {
        TabView select = getSelectedTab();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterPng = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilterPng);

        File file = fileChooser.showSaveDialog(scheduler.getVboxMenu().getScene().getWindow());
        if (file != null) {
            select.getProcessChartView().exportImage(file);
        }
    }

    public void exportSim() {
        TabView select = getSelectedTab();
        try {
            controllerBackend.exportSimulation(new SimulationBackend(select.getSimulationName(), select.getAlgorithmName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TabView getSelectedTab() {
        Tab tab = tabpane.getSelectionModel().getSelectedItem();
        TabView select = null;
        for (TabView tabView : tabViewList) {
            if (tab.equals(tabView.getTab()))
                select = tabView;
        }
        return select;
    }

    public void serializeProcess(String nameAlgorithm, TabView select, ProcessListView list) {
        try {
            serializerJSON.serialize(select.getSimulation().getProcessList());
            if (nameAlgorithm.equals("SJF")) {
                controllerBackend.sjfScheduler(1);
            } else if (nameAlgorithm.equals("Round Robin")) {
                controllerBackend.roundRobinScheduler(2, 3);
            } else if (nameAlgorithm.equals("EDF")) {
                controllerBackend.edfScheduler();
            } else if (nameAlgorithm.equals("FIFO")) {
                controllerBackend.fcfsScheduler();
            } else if (nameAlgorithm.equals("RMS")) {
                controllerBackend.rmsScheduler();
            }
            List<Process> processList = serializerJSON.deserialize();

            for (Process process : processList) {
                select.getProcessChartView().add(process, (int) process.getBurstTime(), process.toHexString(getColor(process.getName(), list.getProcessList())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HBox getHbox(Button exportGraph, Button exportSim, String algorithm) {
        Label label = new Label(algorithm + "                ");
        VBox vBox1 = new VBox();
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox = new HBox();
        hBox.setStyle("-fx-background-color: #ffd500");
        hBox.setMinHeight(50);
        hBox1.getChildren().add(exportGraph);
        hBox1.getChildren().add(exportSim);
        hBox2.getChildren().add(label);
        hBox2.getChildren().add(startButton);
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);

        vBox1.getChildren().add(hBox2);
        vBox1.getChildren().add(hBox1);

        hBox.getChildren().add(vBox1);
        return hBox;
    }


}