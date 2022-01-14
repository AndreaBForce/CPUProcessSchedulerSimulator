package utility;

import service.process.Process;

import java.util.List;

public class SimulationBackend {
    private String name;
    private String algorithmName;
    private List<? extends Process> processList;

    public SimulationBackend() {

    }

    public SimulationBackend(String name, String algorithmName, List<? extends Process> processList) {
        this.name = name;
        this.algorithmName = algorithmName;
        this.processList = processList;
    }

    @Override
    public String toString() {
        return "SimulationBackend{" +
                "name='" + name + '\'' +
                ", algorithmName='" + algorithmName + '\'' +
                ", processList=" + processList +
                '}';
    }
}
