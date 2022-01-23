package utility;

import service.process.ProcessBatch;
import service.process.ProcessInteractive;

import java.util.List;

public class SimulationInteractive extends Simulation {
    private List<ProcessInteractive> processList;

    public SimulationInteractive(String name, String algorithmName, List<ProcessInteractive> processList) {
        super(name, algorithmName);
        this.processList = processList;
    }

    public List<ProcessInteractive> getProcessList() {
        return processList;
    }
}
