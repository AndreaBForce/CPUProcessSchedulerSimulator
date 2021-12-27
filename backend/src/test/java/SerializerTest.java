import controller.SerializerJSON;
import org.junit.Before;
import org.junit.Test;
import service.FCFS.Process;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SerializerTest {
    private final SerializerJSON serializerJSON = new SerializerJSON();
    private final List<Process> processList = new ArrayList<>();

    @Before
    public void init(){
        Process p = new Process("p", 2, 3, 1);
        Process p1 = new Process("p1", 1, 2, 1);
        Process p2 = new Process("p2", 3, 1, 4);
        processList.add(p);
        processList.add(p2);
        processList.add(p1);
    }

    @Test
    public void serializerTest(){
        try {
            serializerJSON.serialize(processList);
            BufferedReader br = new BufferedReader(new FileReader("json"));
            String line = br.readLine();
            br.close();
            assertEquals("[{\"name\":\"p\",\"arrivalTime\":2,\"burstTime\":3,\"priority\":1},{\"name\":\"p2\",\"arrivalTime\":3,\"burstTime\":1,\"priority\":4},{\"name\":\"p1\",\"arrivalTime\":1,\"burstTime\":2,\"priority\":1}]", line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deserializerTest() throws IOException {
            assertEquals(processList, serializerJSON.deserialize());
    }
}
