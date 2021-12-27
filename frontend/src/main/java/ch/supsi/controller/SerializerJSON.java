package ch.supsi.controller;

import ch.supsi.Process;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SerializerJSON {
    private final ObjectMapper mapper = new ObjectMapper();

    public void serialize(List<Process> processList) throws IOException {
        File jsonFile = new File("json");
        for (Process process : processList) {
            mapper.writeValue(jsonFile, process);
        }
    }

    public List<Process> deserialize() throws IOException {
        List<Process> processList = new ArrayList<>();
        File file = new File("json");
        mapper.readValue(file, Process.class);
        return null;
    }
}
