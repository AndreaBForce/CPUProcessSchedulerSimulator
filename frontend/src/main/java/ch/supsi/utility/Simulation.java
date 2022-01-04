package ch.supsi.utility;

import ch.supsi.Process;

import java.util.List;

public class Simulation {
    private String name;
    private String algorithmName;
    private List<Process> processList;

    public Simulation(String name, String algorithmName, List<Process> processList) {
        this.name = name;
        this.algorithmName = algorithmName;
        this.processList = processList;
    }

    public String getName() {
        return name;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public List<Process> getProcessList() {
        return processList;
    }
}
