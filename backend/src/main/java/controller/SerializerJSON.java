package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import service.process.Process;
import service.process.ProcessBatch;

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

    public List<Process> deserialize() throws IOException {
        FileReader file = new FileReader("json");
        BufferedReader br = new BufferedReader(file);
        String line = br.readLine();
        List<Process> processList = gson.fromJson(line, new TypeToken<List<ProcessBatch>>(){}.getType());
        br.close();
        return processList;
    }
}
