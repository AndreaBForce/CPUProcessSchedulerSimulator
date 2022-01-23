import org.junit.Test;
import service.process.ProcessInteractive;
import service.RoundRobin.RoundRobin;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TestRoundRobin {
    @Test
    public void testCompute(){
        RoundRobin roundRobin = new RoundRobin(0.2, 2.5);
        List<ProcessInteractive> processList = new ArrayList<>();
        List<ProcessInteractive> resultList = new ArrayList<>();
        ProcessInteractive p1 = new ProcessInteractive("P1", 5,5);
        ProcessInteractive p2 = new ProcessInteractive("P2", 3,7);
        ProcessInteractive p3 = new ProcessInteractive("P3", 3,1);

        processList.add(p1);
        processList.add(p2);
        processList.add(p3);

        resultList.add(new ProcessInteractive("SP",1.0,0));
        resultList.add(new ProcessInteractive("P3", 3.0,1.0));
        resultList.add(new ProcessInteractive("SP",1.0,4.0));
        resultList.add(new ProcessInteractive("P1",2.5,5.0));
        resultList.add(new ProcessInteractive("CS",0.2,7.5));
        resultList.add(new ProcessInteractive("P2",2.5,7.7));
        resultList.add(new ProcessInteractive("CS",0.2,10.2));
        resultList.add(new ProcessInteractive("P1",2.5,10.4));
        resultList.add(new ProcessInteractive("CS",0.2,12.9));
        resultList.add(new ProcessInteractive("P2",0.5,13.1));

        assertEquals(roundRobin.compute(processList), resultList);

        assertNull(roundRobin.compute(new ArrayList<>()));

    }

}
