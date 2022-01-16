package utility;

import service.process.ProcessBatch;
import service.process.ProcessRealTime;

import java.util.List;

public class SimulationRealTime extends Simulation {
    private List<ProcessRealTime> processList;

    public SimulationRealTime(String name, String algorithmName, List<ProcessRealTime> processList) {
        super(name, algorithmName);
        this.processList = processList;
    }

    public List<ProcessRealTime> getProcessList() {
        return processList;
    }
}
