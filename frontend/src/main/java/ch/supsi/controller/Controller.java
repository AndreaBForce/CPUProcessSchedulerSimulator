package ch.supsi.controller;

import ch.supsi.utility.Mediator;
import controller.ControllerBackend;
import utility.Simulation;

import java.io.IOException;

public class Controller {
    Mediator mediator = new Mediator();
    ControllerBackend controllerBackend = new ControllerBackend();

   public void exportSimulation(Simulation simulation) throws Exception {
       mediator.exportSimulator(simulation);
   }

   public void importScenary() throws IOException {
       mediator.importSimulation();
   }
}
