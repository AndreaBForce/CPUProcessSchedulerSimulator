package controller;

import service.ServiceScheduler;
import utility.Simulation;

import java.io.IOException;

public class ControllerBackend {
    private final ServiceScheduler service = new ServiceScheduler();

    public void fcfsScheduler() {
        service.fcfsSchedulerService();
    }

    public void importSimulation() throws IOException {
        service.importScenary();
    }

    public void exportSimulation(Simulation simulation) throws IOException {
        service.exportScenary(simulation);
    }
}
