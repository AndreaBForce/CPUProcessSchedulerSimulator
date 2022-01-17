package ch.supsi;

import Dialogs.ExitDialog;
import ch.supsi.utility.Simulation;
import javafx.scene.control.Tab;

public class TabView {
    private Tab tab;
    private ExitDialog exit;
    private ProcessChartView processChartView;
    private Simulation simulation;
    private String simulationName;
    private String algorithmName;

    public TabView(String simulationName, String algorithmName) {
        this.simulationName = simulationName;
        this.algorithmName = algorithmName;
        this.tab = new Tab(simulationName);
        processChartView = new ProcessChartView();

        tab.setOnCloseRequest(event -> {
                    exit = new ExitDialog();
                    exit.getStage().showAndWait();
                    if (!exit.isEnd()) {
                        event.consume();
                    }
                }
        );
    }

    public Tab getTab() {
        return tab;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }

    public ExitDialog getExit() {
        return exit;
    }

    public ProcessChartView getProcessChartView() {
        return processChartView;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public String getSimulationName() {
        return simulationName;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
}
