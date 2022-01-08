package utility;

//import service.FCFS.Process;

import java.util.List;

public class SimulationBackend {
    private String name;
    private String algorithmName;
    private List<Process> processList;

    public SimulationBackend(String name, String algorithmName) {
        this.name = name;
        this.algorithmName = algorithmName;
    }

    public SimulationBackend(String name, String algorithmName, List<Process> processList) {
        this.name = name;
        this.algorithmName = algorithmName;
        this.processList = processList;
    }

    public void setProcessList(List<Process> processList) {
        this.processList = processList;
    }
}
