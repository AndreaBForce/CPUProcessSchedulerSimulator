package controller;

import service.ServiceScheduler;
import service.process.*;
import service.process.Process;
import utility.SimulationBackend;

import java.io.IOException;
import java.util.List;

public class ControllerBackend {
    private final ServiceScheduler service = new ServiceScheduler();

    /*public void fcfsScheduler() {
        service.fcfsSchedulerService();
    }

    public void rmsScheduler() {
        service.rmaSchedulerService();
    }

    public void roundRobinScheduler(double contextSwitch, double quantum) {
        service.roundRobinSchedulerService(contextSwitch, quantum);
    }

    public void sjfScheduler(double contextSwitch) {
        service.sjfSchedulerService(contextSwitch);
    }

    public void edfScheduler() {
        service.edfSchedulerService();
    }*/

    public List<Process> rr(double contextSwitch, double quantum, List<ProcessInteractive> processList){
        return service.rr(contextSwitch, quantum, processList);
    }

    public List<Process> fcfs(List<ProcessBatch> processBatches){
        return service.fcfs(processBatches);
    }

    public List<Process> edf(List<ProcessRealTime> processRealTimes){
        return service.edf(processRealTimes);
    }

    public List<Process> rms(List<ProcessRealTime> processRealTimes){
        return service.rms(processRealTimes);
    }

    public List<Process> lottery(double quantum, List<ProcessPriority> list){
        return service.lottery(quantum, list);
    }

    public List<Process> sjf(double contextSwitch, List<ProcessBatch> processBatches){
        return service.sjf(contextSwitch, processBatches);
    }

    public void importSimulation() throws IOException {
        service.importScenary();
    }

    public void exportSimulation(SimulationBackend simulation) throws IOException {
        service.exportScenary(simulation);
    }
}
