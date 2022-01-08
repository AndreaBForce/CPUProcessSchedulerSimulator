package service;

import controller.SerializerJSON;
import repository.Repository;
import service.EDF.EDFScheduler;
import service.FCFS.FCFSAlgorithm;
import service.FCFS.Process;
import service.RMA.RMSScheduler;
import service.RoundRobin.RoundRobin;
import service.SJF.SJF;
import utility.SimulationBackend;

import java.io.IOException;

public class ServiceScheduler {
    private final SerializerJSON serializerJSON = new SerializerJSON();
    private final FCFSAlgorithm fcfsAlgorithm = new FCFSAlgorithm();
    private RoundRobin roundRobin;
    private SJF sjf;
    //private final EDFScheduler edfScheduler = new EDFScheduler();
    //private final RMSScheduler rmsScheduler = new RMSScheduler();
    private final Repository repository = new Repository();

    public void fcfsSchedulerService() {
        try {
            serializerJSON.serialize(fcfsAlgorithm.compute(serializerJSON.deserialize()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void rmaSchedulerService() {
        try {
            serializerJSON.serialize(rmsScheduler.compute(serializerJSON.deserialize()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*public void roundRobinSchedulerService(double contextSwitch, double quantum){
        roundRobin = new RoundRobin(contextSwitch, quantum)
        try {
            serializerJSON.serialize(roundRobin.simulate(serializerJSON.deserialize()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*public void sjfSchedulerService(double contextSwitch){
        sjf = new SJF(contextSwitch);
        try {
            serializerJSON.serialize(sjf.simulate(serializerJSON.deserialize()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*public void edfSchedulerService(){
        try {
            serializerJSON.serialize(edfScheduler.compute(serializerJSON.deserialize()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void importScenary() throws IOException {
        repository.readSimulation();
    }

    public void exportScenary(SimulationBackend simulation) throws IOException {
        repository.writeSimulation(simulation);
    }
}
