import org.junit.Test;
import service.FCFS.FCFSAlgorithm;
import service.process.ProcessBatch;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TestFCFS {

    @Test
    public void testCompute(){
        List<ProcessBatch> processList = new ArrayList<>();
        List<ProcessBatch> resultList = new ArrayList<>();
        FCFSAlgorithm fcfsAlgorithm = new FCFSAlgorithm();

        ProcessBatch p1 = new ProcessBatch("P1",3.0,0.0);
        ProcessBatch p2 = new ProcessBatch("P2",1.0,2.0);
        ProcessBatch p3 = new ProcessBatch("P3",4.0,5.0);
        ProcessBatch p4 = new ProcessBatch("P4",2.0,6.0);
        ProcessBatch p5 = new ProcessBatch("P5",3.0,9.0);

        processList.add(p3);
        processList.add(p2);
        processList.add(p1);
        processList.add(p5);
        processList.add(p4);

        resultList.add(p1);
        resultList.add(p2);
        resultList.add(p3);
        resultList.add(p4);
        resultList.add(p5);

        assertEquals(fcfsAlgorithm.compute(processList), resultList);
    }
}
