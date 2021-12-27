package service;

import controller.SerializerJSON;
import repository.Repository;
import service.FCFS.FCFSAlgorithm;
import utility.Simulation;

import java.io.IOException;

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

    public void importScenary() throws IOException {
        repository.readSimulation();
    }

    public void exportScenary(Simulation simulation) throws IOException {
        repository.writeSimulation(simulation);
    }

}
