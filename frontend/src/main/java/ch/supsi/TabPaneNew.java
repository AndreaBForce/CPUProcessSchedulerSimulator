package ch.supsi;

import Dialogs.CreateSimDialog;
import ch.supsi.controller.Mediator;
import ch.supsi.utility.Simulation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import service.process.ProcessBatch;
import service.process.ProcessInteractive;
import service.process.ProcessPriority;
import service.process.ProcessRealTime;
import utility.SimulationBackend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class TabPaneNew {
    private TabPane tabpane;
    private List<TabView> tabViewList = new ArrayList<>();
    private Button startButton;
    private Mediator mediator;
    private Scheduler scheduler;

    public TabPaneNew(Scheduler scheduler, Mediator mediator) {
        this.mediator = mediator;
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

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public void createRandomSimulation() {
        ProcessListView list = new ProcessListView();
        String[] algorithms = {"RMA", "Lottery", "FCFS", "EDF", "SJF", "Round Robin"};
        int algorithm = (int) (Math.random() * 6);
        int numProcess = (int) (Math.random() * 5) + 1;
        list.setAlgortihm(algorithms[algorithm]);
        for (int i = 0; i < numProcess; i++) {
            list.add(new Process("p" + i, roundAvoid((double) (Math.random() * 10.9) + 0.1, 1), roundAvoid((double) (Math.random() * 10.9) + 0.1, 1), (int) (Math.random() * 11), Color.color(Math.random(), Math.random(), Math.random())));
        }

        Button exportGraph = new Button("Export graph");
        exportGraph.setDisable(true);
        Button exportSim = new Button("Export simulation");
        exportSim.setDisable(true);
        TabView newTab = new TabView("random", algorithms[algorithm]);
        tabViewList.add(newTab);
        startButton = new Button("start");
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
            computeAlgorithm("random", algorithms[algorithm], select, list);
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

        startButton = new Button("start");
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

            computeAlgorithm(nameSimulation, nameAlgorithm, select, list);
        });
    }

    public void computeAlgorithm(String nameSimulation, String nameAlgorithm, TabView select, ProcessListView list) {
        if (nameAlgorithm.equals("Round Robin")) {
            List<ProcessInteractive> processInteractives = new ArrayList<>();
            for (Process process : list.getProcessList()) {
                processInteractives.add(new ProcessInteractive(process.getName(), process.getBurstTime(), process.getArrivalTime()));
            }
            select.setSimulation(new Simulation(nameSimulation, nameAlgorithm, processInteractives));
            serializeProcess(nameAlgorithm, select, processInteractives, list);
        } else if (nameAlgorithm.equals("FIFO") || nameAlgorithm.equals("SJF")) {
            List<ProcessBatch> processInteractives = new ArrayList<>();
            for (Process process : list.getProcessList()) {
                processInteractives.add(new ProcessBatch(process.getName(), process.getBurstTime(), process.getArrivalTime()));
            }
            select.setSimulation(new Simulation(nameSimulation, nameAlgorithm, processInteractives));
            serializeProcess(nameAlgorithm, select, processInteractives, list);
        } else if (nameAlgorithm.equals("EDF") || nameAlgorithm.equals("RMA")) {
            List<ProcessRealTime> processInteractives = new ArrayList<>();
            for (Process process : list.getProcessList()) {
                processInteractives.add(new ProcessRealTime(process.getName(), process.getPriority(), process.getBurstTime()));
            }
            select.setSimulation(new Simulation(nameSimulation, nameAlgorithm, processInteractives));
            serializeProcess(nameAlgorithm, select, processInteractives, list);
        } else {
            List<ProcessPriority> processInteractives = new ArrayList<>();
            for (Process process : list.getProcessList()) {
                processInteractives.add(new ProcessPriority(process.getName(), process.getBurstTime(), process.getPriority()));
            }
            select.setSimulation(new Simulation(nameSimulation, nameAlgorithm, processInteractives));
            serializeProcess(nameAlgorithm, select, processInteractives, list);
        }
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
            mediator.exportSimulation(new SimulationBackend(select.getSimulationName(), select.getAlgorithmName(), select.getSimulation().getProcessList()));
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

    public void serializeProcess(String nameAlgorithm, TabView select, List<? extends service.process.Process> processList, ProcessListView list) {
        //TODO PARAMS ------ ROUND ROBIN ----- SJF ------ LOTTERY
        List<service.process.Process> processList1 = null;
        if (nameAlgorithm.equals("SJF")) {
            processList1 = mediator.sjfScheduler(0.2, (List<ProcessBatch>) processList);
        } else if (nameAlgorithm.equals("Round Robin")) {
            processList1 = mediator.roundRobinScheduler(0.2, 2.5, (List<ProcessInteractive>) processList);
        } else if (nameAlgorithm.equals("EDF")) {
            processList1 = mediator.edfScheduler((List<ProcessRealTime>) processList);
        } else if (nameAlgorithm.equals("FIFO")) {
            processList1 = mediator.fcfsScheduler((List<ProcessBatch>) processList);
        } else if (nameAlgorithm.equals("RMA")) {
            processList1 = mediator.rmsScheduler((List<ProcessRealTime>) processList);
        }else {
            processList1 = mediator.lotteryScheduler(2.0, (List<ProcessPriority>) processList);
        }

        for (service.process.Process process : processList1) {
            select.getProcessChartView().add(process, process.getBurstTime(), toHexString(getColor(process.getName(), list.getProcessList())));
        }
    }

    private String format(double val) {
        String in = Integer.toHexString((int) Math.round(val * 255));
        return in.length() == 1 ? "0" + in : in;
    }

    public String toHexString(Color value) {
        return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()))
                .toUpperCase();
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