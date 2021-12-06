import org.junit.Before;
import org.junit.Test;
import service.RMA.ProcessBackend;
import service.RMA.RMSScheduler;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestRMA {
    ArrayList<ProcessBackend> listaProcessi = new ArrayList<>();
    ArrayList<ProcessBackend> listaProcessi2 = new ArrayList<>();

    RMSScheduler rms;
    RMSScheduler rms2;

    @Before
    public void init() {
        ProcessBackend pA = new ProcessBackend("A", 20, 3);
        ProcessBackend pB = new ProcessBackend("B", 5, 1);
        ProcessBackend pC = new ProcessBackend("C", 14, 4);

        listaProcessi.add(pA);
        listaProcessi.add(pB);
        listaProcessi.add(pC);

        listaProcessi2.add(pB);
        listaProcessi2.add(pC);
        listaProcessi2.add(pA);


        rms = new RMSScheduler(listaProcessi);
        rms2 = new RMSScheduler(listaProcessi2);
    }

    @Test
    public void testSchedulable(){
        assertEquals(true,rms.isSchedulable());
    }


    @Test
    public void testRMA(){
        assertEquals(rms.getSimulationArray(),rms2.getSimulationArray());
    }

}
