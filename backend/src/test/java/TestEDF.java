import org.junit.Before;
import org.junit.Test;
import service.EDF.EDFScheduler;
import service.process.ProcessRealTime;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestEDF {

    ArrayList<ProcessRealTime> listaProcessi = new ArrayList<>();
    ArrayList<ProcessRealTime> listaProcessi2 = new ArrayList<>();

    EDFScheduler edf;
//    EDFScheduler edf2;

    @Before
    public void init() {
        ProcessRealTime pA = new ProcessRealTime("A", 6, 3);
        ProcessRealTime pB = new ProcessRealTime("B", 8, 3);
        ProcessRealTime pC = new ProcessRealTime("C", 10, 1);

        listaProcessi.add(pA);
        listaProcessi.add(pB);
        listaProcessi.add(pC);

        listaProcessi2.add(pB);
        listaProcessi2.add(pC);
        listaProcessi2.add(pA);


        edf = new EDFScheduler();
//        edf2 = new EDFScheduler();
    }

    @Test
    public void testSchedulable(){
        assertTrue(edf.isSchedulable(listaProcessi));
    }


    @Test
    public void testEDF(){
        assertEquals(edf.compute(listaProcessi),edf.compute(listaProcessi2));
    }


}
