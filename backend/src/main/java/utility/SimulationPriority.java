package utility;

import service.process.ProcessBatch;
import service.process.ProcessPriority;

import java.util.List;

public class SimulationPriority extends Simulation {
    private List<ProcessPriority> processList;

    public SimulationPriority(String name, String algorithmName, List<ProcessPriority> processList) {
        super(name, algorithmName);
        this.processList = processList;
    }

    public List<ProcessPriority> getProcessList() {
        return processList;
    }
}
