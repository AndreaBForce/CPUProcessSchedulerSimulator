package ch.supsi.controller;

import controller.ControllerBackend;
import service.process.*;
import service.process.Process;
import utility.SimulationBackend;

import java.io.IOException;
import java.util.List;

public class Mediator {
    private ControllerBackend controllerBackend = new ControllerBackend();

    public List<Process> roundRobinScheduler(double contextSwitch, double quantum, List<ProcessInteractive> processList){
        return controllerBackend.roundRobinScheduler(contextSwitch, quantum, processList);
    }

    public List<Process> fcfsScheduler(List<ProcessBatch> processBatches){
        return controllerBackend.fcfsScheduler(processBatches);
    }

    public List<Process> edfScheduler(List<ProcessRealTime> processRealTimes){
        return controllerBackend.edfScheduler(processRealTimes);
    }

    public List<Process> rmsScheduler(List<ProcessRealTime> processRealTimes){
        return controllerBackend.rmsScheduler(processRealTimes);
    }

    public List<Process> lotteryScheduler(double quantum, List<ProcessPriority> list){
        return controllerBackend.lotteryScheduler(quantum, list);
    }

    public List<Process> sjfScheduler(double contextSwitch, List<ProcessBatch> processBatches){
        return controllerBackend.sjfScheduler(contextSwitch, processBatches);
    }

    public void exportSimulation(SimulationBackend simulation) throws IOException {
        controllerBackend.exportSimulation(simulation);
    }

    public void importSimulation() throws IOException {
        controllerBackend.importSimulation();
    }
}
