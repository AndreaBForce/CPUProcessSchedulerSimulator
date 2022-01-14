package utility;

import service.process.Process;
import service.process.ProcessBatch;

import java.util.List;

public class Simulation {
    private String name;
    private String algorithmName;
    private List<ProcessBatch> processList;

    public Simulation(String name, String algorithmName, List<ProcessBatch> processList) {
        this.name = name;
        this.algorithmName = algorithmName;
        this.processList = processList;
    }

    @Override
    public String toString() {
        return "Simulation{" +
                "name='" + name + '\'' +
                ", algorithmName='" + algorithmName + '\'' +
                ", processList=" + processList +
                '}';
    }
}
