package service;

import repository.Repository;
import service.EDF.EDFScheduler;
import service.FCFS.FCFSAlgorithm;
import service.Lottery.Lottery;
import service.RMA.RMSScheduler;
import service.RoundRobin.RoundRobin;
import service.SJF.SJF;
import service.process.*;
import service.process.Process;
import utility.SimulationBackend;

import java.io.IOException;
import java.util.List;

public class ServiceScheduler {
    private final FCFSAlgorithm fcfsAlgorithm = new FCFSAlgorithm();
    private RoundRobin roundRobin;
    private SJF sjf;
    private Lottery lottery;
    private final EDFScheduler edfScheduler = new EDFScheduler();
    private final RMSScheduler rmsScheduler = new RMSScheduler();
    private final Repository repository = new Repository();

    public List<Process> rr(double contextSwitch, double quantum, List<ProcessInteractive> processInteractives){
        roundRobin = new RoundRobin(contextSwitch, quantum);
        return roundRobin.compute(processInteractives);
    }

    public List<Process> fcfs(List<ProcessBatch> processBatches){
        return fcfsAlgorithm.compute(processBatches);
    }

    public List<Process> edf(List<ProcessRealTime> processRealTimes){
        return edfScheduler.compute(processRealTimes);
    }

    public List<Process> rms(List<ProcessRealTime> processRealTimes){
        return rmsScheduler.compute(processRealTimes);
    }

    public List<Process> lottery(double quantum, List<ProcessPriority> list){
        lottery = new Lottery(quantum);
        return lottery.compute(list);
    }

    public List<Process> sjf(double contextSwitch, List<ProcessBatch> processBatches){
        sjf = new SJF(contextSwitch);
        return sjf.compute(processBatches);
    }


    public void importScenary() throws IOException {
        repository.readSimulation();
    }

    public void exportScenary(SimulationBackend simulation) throws IOException {
        repository.writeSimulation(simulation);
    }
}
