package service;

import controller.SerializerJSON;
import repository.Repository;
import service.FCFS.FCFSAlgorithm;
import service.FCFS.Process;
import utility.SimulationBackend;

import java.io.IOException;
import java.util.List;

public class ServiceScheduler {
    private final SerializerJSON serializerJSON = new SerializerJSON();
    private final FCFSAlgorithm fcfsAlgorithm = new FCFSAlgorithm();
    private final Repository repository = new Repository();

    public void fcfsSchedulerService(){
        try {
            serializerJSON.serialize(fcfsAlgorithm.schedulerFCFS(serializerJSON.deserialize()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Process> fcfsSchedulerTest(List<Process> processList){
        return fcfsAlgorithm.schedulerFCFS(processList);
    }

    public void importScenary() throws IOException {
        repository.readSimulation();
    }

    public void exportScenary(SimulationBackend simulation) throws IOException {
        repository.writeSimulation(simulation);
    }

}
