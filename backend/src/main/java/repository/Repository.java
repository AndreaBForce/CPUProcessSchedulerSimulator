package repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;;
import utility.*;

import java.io.*;

public class Repository {
    private final Gson gson = new Gson();

    public void writeSimulation(Simulation simulation, String name) throws IOException {
        String json;
        if (simulation.getAlgorithmName().equals("SJF") || simulation.getAlgorithmName().equals("FCFS")) {
            SimulationBatch simulationBatch = (SimulationBatch) simulation;
            json = gson.toJson(simulationBatch);
        } else if (simulation.getAlgorithmName().equals("EDF") || simulation.getAlgorithmName().equals("RMA")) {
            SimulationRealTime simulationRealTime = (SimulationRealTime) simulation;
            json = gson.toJson(simulationRealTime);
        } else if (simulation.getAlgorithmName().equals("Lottery")) {
            SimulationPriority simulationPriority = (SimulationPriority) simulation;
            json = gson.toJson(simulationPriority);
        } else {
            SimulationInteractive simulationInteractive = (SimulationInteractive) simulation;
            json = gson.toJson(simulationInteractive);
        }

        FileWriter jsonFile = new FileWriter(name);
        jsonFile.write(json);
        jsonFile.close();
    }

    public Simulation readSimulation(String name) throws IOException {
        try {
            FileReader file = new FileReader(name);
            BufferedReader br = new BufferedReader(file);
            String line = br.readLine();
            String[] lines = line.split(",");
            int index = 0;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i].charAt(1) == 'a' && lines[i].charAt(2) == 'l')
                    index = i;
            }
            int i = 17;
            StringBuilder nameAlg = new StringBuilder();
            while (lines[index].charAt(i) != '\"') {
                nameAlg.append(lines[index].charAt(i));
                i++;
            }

            if (nameAlg.toString().equals("SJF") || nameAlg.toString().equals("FCFS")) {
                SimulationBatch s = gson.fromJson(line, new TypeToken<SimulationBatch>() {
                }.getType());
                br.close();
                return s;
            } else if (nameAlg.toString().equals("EDF") || nameAlg.toString().equals("RMA")) {
                SimulationRealTime s = gson.fromJson(line, new TypeToken<SimulationRealTime>() {
                }.getType());
                br.close();
                return s;
            } else if (nameAlg.toString().equals("Lottery")) {
                SimulationPriority s = gson.fromJson(line, new TypeToken<SimulationPriority>() {
                }.getType());
                br.close();
                return s;
            } else {
                SimulationInteractive s = gson.fromJson(line, new TypeToken<SimulationInteractive>() {
                }.getType());
                br.close();
                return s;
            }
        } catch (FileNotFoundException e) {
            return null;
        }
    }

}
