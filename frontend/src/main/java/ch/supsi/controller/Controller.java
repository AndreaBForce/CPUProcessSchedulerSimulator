package ch.supsi.controller;

import ch.supsi.utility.Mediator;
import controller.ControllerBackend;
import utility.SimulationBackend;

import java.io.IOException;

public class Controller {
    Mediator mediator = new Mediator();

    public void exportSimulation(SimulationBackend simulation) throws Exception {
        mediator.exportSimulator(simulation);
    }

    public void importScenary() throws IOException {
        mediator.importSimulation();
    }
}
