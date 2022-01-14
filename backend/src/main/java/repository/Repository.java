package repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;;
import utility.Simulation;
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

    public Simulation readSimulation() throws IOException {
        FileReader file = new FileReader("simulation");
        BufferedReader br = new BufferedReader(file);
        String line = br.readLine();
        String [] lines = line.split(",");
        int i = 17;
        StringBuilder nameAlg = new StringBuilder();
        while(lines[1].charAt(i) != '\"'){
            nameAlg.append(lines[1].charAt(i));
            i++;
        }
        Simulation simulation = null;
        if(nameAlg.toString().equals("SJF")) {
            simulation = gson.fromJson(line, new TypeToken<Simulation>() {
            }.getType());
            System.out.println(simulation);

        }
        br.close();
        return simulation;
    }

}
