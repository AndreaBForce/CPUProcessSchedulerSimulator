package service;

import controller.SerializerJSON;
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
    private final SerializerJSON serializerJSON = new SerializerJSON();
    private final FCFSAlgorithm fcfsAlgorithm = new FCFSAlgorithm();
    private RoundRobin roundRobin;
    private SJF sjf;
    private Lottery lottery;
    private final EDFScheduler edfScheduler = new EDFScheduler();
    private final RMSScheduler rmsScheduler = new RMSScheduler();
    private final Repository repository = new Repository();

    /*public void fcfsSchedulerService() {
        try {
            serializerJSON.serialize(fcfsAlgorithm.compute(serializerJSON.deserialize("FCFS")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rmaSchedulerService() {
        try {
            serializerJSON.serialize(rmsScheduler.compute(serializerJSON.deserialize("RMS")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void roundRobinSchedulerService(double contextSwitch, double quantum){
        roundRobin = new RoundRobin(contextSwitch, quantum);
        try {
            serializerJSON.serialize(roundRobin.compute(serializerJSON.deserialize("Round Robin")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void sjfSchedulerService(double contextSwitch){
        sjf = new SJF(contextSwitch);
        try {
            serializerJSON.serialize(sjf.compute(serializerJSON.deserialize("SJF")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void edfSchedulerService(){
        try {
            serializerJSON.serialize(edfScheduler.compute(serializerJSON.deserialize("EDF")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

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
