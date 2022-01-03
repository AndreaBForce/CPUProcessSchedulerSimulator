import org.junit.Test;
import service.process.ProcessBatch;
import service.SJF.SJF;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TestSJF {

    @Test
    public void testCompute(){
        List<ProcessBatch> process = new ArrayList<>();
        List<ProcessBatch> resultList = new ArrayList<>();

        SJF sjf = new SJF(0.2);

        ProcessBatch p1 = new ProcessBatch("P1",6, 11);
        ProcessBatch p2 = new ProcessBatch("P2",2, 5);
        ProcessBatch p3 = new ProcessBatch("P3",8, 11);
        ProcessBatch p4 = new ProcessBatch("P4",3, 1);
        ProcessBatch p5 = new ProcessBatch("P5",4, 4);

        process.add(p1);
        process.add(p2);
        process.add(p3);
        process.add(p4);
        process.add(p5);

        resultList.add(new ProcessBatch("SP", 1,0));
        resultList.add(new ProcessBatch("P4", 3,1));
        resultList.add(new ProcessBatch("CS", 0.2,4));
        resultList.add(new ProcessBatch("P5", 4,4));
        resultList.add(new ProcessBatch("CS", 0.2,8.2));
        resultList.add(new ProcessBatch("P2", 2.0,5.0));
        resultList.add(new ProcessBatch("SP", 0.6,10.4));
        resultList.add(new ProcessBatch("P1", 6,11));
        resultList.add(new ProcessBatch("CS", 0.2,17));
        resultList.add(new ProcessBatch("P3", 8,11));

        assertEquals(sjf.compute(process), resultList);
        assertNull(sjf.compute(new ArrayList<>()));
    }
}
