package ch.supsi.utility;

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
}
