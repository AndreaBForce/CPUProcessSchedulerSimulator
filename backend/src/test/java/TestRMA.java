import org.junit.Before;
import org.junit.Test;
import service.process.ProcessRealTime;
import service.RMA.RMSScheduler;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestRMA {
    ArrayList<ProcessRealTime> listaProcessi = new ArrayList<>();
    ArrayList<ProcessRealTime> listaProcessi2 = new ArrayList<>();

    RMSScheduler rms;
//    RMSScheduler rms2;

    @Before
    public void init() {
        ProcessRealTime pA = new ProcessRealTime("A", 20, 3);
        ProcessRealTime pB = new ProcessRealTime("B", 5, 1);
        ProcessRealTime pC = new ProcessRealTime("C", 14, 4);

        listaProcessi.add(pA);
        listaProcessi.add(pB);
        listaProcessi.add(pC);

        listaProcessi2.add(pB);
        listaProcessi2.add(pC);
        listaProcessi2.add(pA);


        rms = new RMSScheduler();
//        rms2 = new RMSScheduler();
    }

    @Test
    public void testSchedulable(){
        assertTrue(rms.isSchedulable(listaProcessi));
    }


    @Test
    public void testRMA(){
        assertEquals(rms.compute(listaProcessi),rms.compute(listaProcessi2));
    }

}
