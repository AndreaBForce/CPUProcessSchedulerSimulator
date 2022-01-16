package utility;

import service.process.ProcessBatch;

import java.util.List;

public class SimulationBatch extends Simulation {
    private List<ProcessBatch> processList;

    public SimulationBatch(String name, String algorithmName, List<ProcessBatch> processList) {
        super(name, algorithmName);
        this.processList = processList;
    }

    public List<ProcessBatch> getProcessList() {
        return processList;
    }
}
