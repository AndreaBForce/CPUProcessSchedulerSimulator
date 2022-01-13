package ch.supsi.controller;

import ch.supsi.Process;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.List;

public class SerializerJSON {
    private final Gson gson = new Gson();

    public void serialize(List<ch.supsi.Process> processList) throws IOException {
        String json = gson.toJson(processList);
        FileWriter jsonFile = new FileWriter("json");
        jsonFile.write(json);
        jsonFile.close();
    }

    public List<ch.supsi.Process> deserialize() throws IOException {
        FileReader file = new FileReader("json");
        BufferedReader br = new BufferedReader(file);
        String line = br.readLine();
        List <ch.supsi.Process> processList = gson.fromJson(line, new TypeToken<List<Process>>(){}.getType());
        br.close();
        return processList;
    }
}
