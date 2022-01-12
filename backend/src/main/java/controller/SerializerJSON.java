package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import service.process.Process;
import service.process.ProcessBatch;
import service.process.ProcessInteractive;
import service.process.ProcessRealTime;

import java.io.*;
import java.util.List;

public class SerializerJSON {
    private final Gson gson = new Gson();

    public void serialize(List<Process> processList) throws IOException {
        String json = gson.toJson(processList);
        FileWriter jsonFile = new FileWriter("json");
        jsonFile.write(json);
        jsonFile.close();
    }

    public List<Process> deserialize(String nameAlgorithm) throws IOException {
        FileReader file = new FileReader("json");
        BufferedReader br = new BufferedReader(file);
        String line = br.readLine();

        List<Process> processList = null;
        switch (nameAlgorithm) {
            case "EDF" -> processList = gson.fromJson(line, new TypeToken<List<ProcessRealTime>>() {
            }.getType());
            case "FCFS" -> processList = gson.fromJson(line, new TypeToken<List<ProcessBatch>>() {
            }.getType());
            case "Round Robin" -> processList = gson.fromJson(line, new TypeToken<List<ProcessInteractive>>() {
            }.getType());
            case "SJF" -> processList = gson.fromJson(line, new TypeToken<List<ProcessBatch>>() {
            }.getType());
            case "RMS" -> processList = gson.fromJson(line, new TypeToken<List<ProcessRealTime>>() {
            }.getType());
            default -> {
            }
        }

        br.close();
        return processList;
    }
}
