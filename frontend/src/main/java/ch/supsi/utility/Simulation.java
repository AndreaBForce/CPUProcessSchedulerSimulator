package ch.supsi.utility;

import service.process.Process;

import java.util.List;

public class Simulation {
    private String name;
    private String algorithmName;
    private List<? extends service.process.Process> processList;

    public Simulation(String name, String algorithmName, List<? extends service.process.Process> processList) {
        this.name = name;
        this.algorithmName = algorithmName;
        this.processList = processList;
    }

    public String getName() {
        return name;
    }

    public List<? extends Process> getProcessList() {
        return processList;
    }
}
