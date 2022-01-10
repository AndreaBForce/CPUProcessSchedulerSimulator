package controller;

import service.ServiceScheduler;
import utility.SimulationBackend;

import java.io.IOException;

public class ControllerBackend {
    private final ServiceScheduler service = new ServiceScheduler();

    public void fcfsScheduler() {

        System.out.println("ciao");
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
    }

    public void importSimulation() throws IOException {
        service.importScenary();
    }

    public void exportSimulation(SimulationBackend simulation) throws IOException {
        service.exportScenary(simulation);
    }
}
