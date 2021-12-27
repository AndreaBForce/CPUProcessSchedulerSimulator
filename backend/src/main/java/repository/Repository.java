package repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;;
import utility.Simulation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Repository {
    private final Gson gson = new Gson();

    public void writeSimulation(Simulation simulation) throws IOException {
        String json = gson.toJson(simulation);
        FileWriter jsonFile = new FileWriter("simulation");
        jsonFile.write(json);
        jsonFile.close();
    }

    public Simulation readSimulation() throws IOException {
        FileReader file = new FileReader("simulation");
        BufferedReader br = new BufferedReader(file);
        String line = br.readLine();
        Simulation simulation = gson.fromJson(line, new TypeToken<Simulation>(){}.getType());
        br.close();
        return simulation;
    }

}
