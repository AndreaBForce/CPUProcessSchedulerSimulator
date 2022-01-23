package controller;

import service.ServiceScheduler;
import service.process.*;
import service.process.Process;
import utility.Simulation;
import utility.SimulationBackend;

import java.io.IOException;
import java.util.List;

public class ControllerBackend {
    private final ServiceScheduler service = new ServiceScheduler();

    public List<Process> roundRobinScheduler(double contextSwitch, double quantum, List<ProcessInteractive> processList) {
        return service.rr(contextSwitch, quantum, processList);
    }

    public List<Process> fcfsScheduler(double contextSwitch, List<ProcessBatch> processBatches) {
        return service.fcfs(contextSwitch, processBatches);
    }

    public List<Process> edfScheduler(List<ProcessRealTime> processRealTimes) {
        return service.edf(processRealTimes);
    }

    public List<Process> rmsScheduler(List<ProcessRealTime> processRealTimes) {
        return service.rms(processRealTimes);
    }

    public List<Process> lotteryScheduler(double quantum, List<ProcessPriority> list) {
        return service.lottery(quantum, list);
    }

    public List<Process> sjfScheduler(double contextSwitch, List<ProcessBatch> processBatches) {
        return service.sjf(contextSwitch, processBatches);
    }

    public Simulation importSimulation(String name) throws IOException {
        return service.importScenary(name);
    }

    public void exportSimulation(Simulation simulation, String name) throws IOException {
        service.exportScenary(simulation, name);
    }
}
