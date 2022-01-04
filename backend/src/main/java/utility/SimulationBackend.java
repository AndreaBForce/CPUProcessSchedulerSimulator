package utility;

import service.FCFS.Process;

import java.util.List;

public class Simulation {
    private String name;
    private String algorithmName;
    private List<Process> processList;

    public Simulation(String name, String algorithmName) {
        this.name = name;
        this.algorithmName = algorithmName;
    }

    public Simulation(String name, String algorithmName, List<Process> processList) {
        this.name = name;
        this.algorithmName = algorithmName;
        this.processList = processList;
    }

    public void setProcessList(List<Process> processList) {
        this.processList = processList;
    }
}
