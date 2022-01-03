import com.google.gson.Gson;
import controller.SerializerJSON;
import org.junit.Before;
import org.junit.Test;
import service.process.Process;
import service.process.ProcessBatch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SerializerTest {
    private final SerializerJSON serializerJSON = new SerializerJSON();
    private final Gson gson = new Gson();
    private final List<Process> processList = new ArrayList<>();

    @Before
    public void init(){
        Process p = new ProcessBatch("p", 2, 3);
        Process p1 = new ProcessBatch("p1", 1, 2);
        Process p2 = new ProcessBatch("p2", 3, 1);
        processList.add(p);
        processList.add(p2);
        processList.add(p1);
    }

    @Test
    public void serializerTest(){
        try (BufferedReader br = new BufferedReader(new FileReader("json"))) {
            serializerJSON.serialize(processList);
            String line = br.readLine();
            String expected = gson.toJson(processList);
            assertEquals(expected, line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deserializerTest() throws IOException {
            assertEquals(processList, serializerJSON.deserialize());
    }
}
