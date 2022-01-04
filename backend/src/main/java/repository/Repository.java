package repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;;
import utility.SimulationBackend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Repository {
    private final Gson gson = new Gson();

    public void writeSimulation(SimulationBackend simulation) throws IOException {
        String json = gson.toJson(simulation);
        FileWriter jsonFile = new FileWriter("simulation");
        jsonFile.write(json);
        jsonFile.close();
    }

    public SimulationBackend readSimulation() throws IOException {
        FileReader file = new FileReader("simulation");
        BufferedReader br = new BufferedReader(file);
        String line = br.readLine();
        SimulationBackend simulation = gson.fromJson(line, new TypeToken<SimulationBackend>(){}.getType());
        br.close();
        return simulation;
    }

}
