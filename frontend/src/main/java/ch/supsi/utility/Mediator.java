package ch.supsi.utility;

import ch.supsi.controller.Controller;
import controller.ControllerBackend;
import utility.SimulationBackend;

import java.io.IOException;

public class Mediator {
    private Controller controller = new Controller();
    private ControllerBackend controllerBackend = new ControllerBackend();

    public void exportSimulator(SimulationBackend simulation) throws IOException {
        controllerBackend.exportSimulation(simulation);
    }

    public void importSimulation() throws IOException {
        controllerBackend.importSimulation();
    }

    public void fcfsAlgorithm(){
        controllerBackend.fcfsScheduler();
    }

}
