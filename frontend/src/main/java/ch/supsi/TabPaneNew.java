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
    private Button startButton;
    private ControllerBackend controllerBackend;
    private Scheduler scheduler;

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

    public void createNewSimulation(String nameSimulation, String nameAlgorithm, boolean confirmed) {
        ProcessListView list = new ProcessListView();
        Button exportGraph = new Button("Export graph");
        exportGraph.setDisable(true);
        Button exportSim = new Button("Export simulation");
        exportSim.setDisable(true);
        TabView newTab = new TabView(nameSimulation, nameAlgorithm);
        tabViewList.add(newTab);
        if (confirmed) {
            newTab.getProcessChartView().testChart();
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.BOTTOM_CENTER);
            vBox.setStyle("-fx-background-color: #f8fce5");
            VBox.setVgrow(list.getContainer(), Priority.ALWAYS);

            Label label = new Label(nameAlgorithm + "                ");
            startButton = new Button("start");

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

            vBox.getChildren().addAll(list.getContainer(), hBox, newTab.getProcessChartView().getChart());
            newTab.getTab().setContent(vBox);
            addTab(newTab);
        }

        exportGraph.setOnMouseClicked(mouseEvent -> {
            Tab tab = tabpane.getSelectionModel().getSelectedItem();
            TabView select = null;
            for (TabView tabView : tabViewList) {
                if (tab.equals(tabView.getTab()))
                    select = tabView;
            }
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilterPng = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
            FileChooser.ExtensionFilter extFilterJpeg = new FileChooser.ExtensionFilter("Jpeg files (*.jpeg)", "*.jpeg");
            fileChooser.getExtensionFilters().add(extFilterPng);
            fileChooser.getExtensionFilters().add(extFilterJpeg);

            File file = fileChooser.showSaveDialog(scheduler.getVboxMenu().getScene().getWindow());
            if (file != null) {
                select.getProcessChartView().exportImage(file);
            }
        });

        exportSim.setOnMouseClicked(mouseEvent -> {
            Tab tab = tabpane.getSelectionModel().getSelectedItem();
            TabView select = null;
            for (TabView tabView : tabViewList) {
                if (tab.equals(tabView.getTab()))
                    select = tabView;
            }
            try {
                controllerBackend.exportSimulation(new SimulationBackend(select.getSimulation().getName(), select.getSimulation().getAlgorithmName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        startButton.setOnMouseClicked(mouseEvent -> {
            exportGraph.setDisable(false);
            exportSim.setDisable(false);
            Tab tab = tabpane.getSelectionModel().getSelectedItem();
            TabView select = null;
            for (TabView tabView : tabViewList) {
                if (tab.equals(tabView.getTab()))
                    select = tabView;
            }
            select.setSimulation(new Simulation(nameSimulation, nameAlgorithm, list.getProcessList()));
            SerializerJSON serializerJSON = new SerializerJSON();
            try {
                serializerJSON.serialize(select.getSimulation().getProcessList());
                controllerBackend.fcfsScheduler();
                List<Process> processList = serializerJSON.deserialize();

                for (Process process : processList) {
                    select.getProcessChartView().add(process, (int) process.getBurstTime(), "yellow");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}