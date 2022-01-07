package ch.supsi;

import Dialogs.ExitDialog;
import ch.supsi.utility.Simulation;
import javafx.scene.control.Tab;

public class TabView {
    private Tab tab;
    private ExitDialog exit;
    private ProcessChartView processChartView;
    private Simulation simulation;
    private ProcessListView processListView;
    private String nameSimulation;
    private String nameAlgorithm;

    public TabView(String nome, String nameAlgorithm) {
        nameSimulation = nome;
        this.nameAlgorithm = nameAlgorithm;
        this.tab = new Tab(nome);
        processChartView = new ProcessChartView();

        tab.setOnCloseRequest(event -> {
            exit = new ExitDialog();

            exit.getExitStage().showAndWait();


                if(!exit.isEnd()) {

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

    public String getNameSimulation() {
        return nameSimulation;
    }

    public String getNameAlgorithm() {
        return nameAlgorithm;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
}
