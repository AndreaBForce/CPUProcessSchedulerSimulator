import org.junit.Before;
import org.junit.Test;
import service.EDF.EDFScheduler;
import service.EDF.ProcessBackend;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestEDF {

    ArrayList<ProcessBackend> listaProcessi = new ArrayList<>();
    ArrayList<ProcessBackend> listaProcessi2 = new ArrayList<>();

    EDFScheduler edf;
    EDFScheduler edf2;

    @Before
    public void init() {
        ProcessBackend pA = new ProcessBackend("A", 6, 3);
        ProcessBackend pB = new ProcessBackend("B", 8, 3);
        ProcessBackend pC = new ProcessBackend("C", 10, 1);

        listaProcessi.add(pA);
        listaProcessi.add(pB);
        listaProcessi.add(pC);

        listaProcessi2.add(pB);
        listaProcessi2.add(pC);
        listaProcessi2.add(pA);


        edf = new EDFScheduler(listaProcessi);
        edf2 = new EDFScheduler(listaProcessi2);
    }

    @Test
    public void testSchedulable(){
        assertEquals(true,edf.isSchedulable());
    }


    @Test
    public void testEDF(){
        assertEquals(edf.getSimulationArray(),edf2.getSimulationArray());
    }


}
