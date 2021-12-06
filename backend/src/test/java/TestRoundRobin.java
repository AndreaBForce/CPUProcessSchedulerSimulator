import RoundRobin.*;
import org.junit.Test;
import service.RoundRobin.ProcessBack2;
import service.RoundRobin.RoundRobin;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TestRoundRobin {
    @Test
    public void testSimulate(){
        RoundRobin roundRobin = new RoundRobin(0.2, 2.5);
        List<ProcessBack2> processList = new ArrayList<>();
        List<ProcessBack2> resultList = new ArrayList<>();
        ProcessBack2 p1 = new ProcessBack2("P1", 5,5);
        ProcessBack2 p2 = new ProcessBack2("P2", 3,7);
        ProcessBack2 p3 = new ProcessBack2("P3", 3,1);

        processList.add(p1);
        processList.add(p2);
        processList.add(p3);

        resultList.add(new ProcessBack2("",1.0,0));
        resultList.add(new ProcessBack2("P3", 3.0,1.0));
        resultList.add(new ProcessBack2("",1.0,4.0));
        resultList.add(new ProcessBack2("P1",2.5,5.0));
        resultList.add(new ProcessBack2("CS",0.2,7.5));
        resultList.add(new ProcessBack2("P2",2.5,7.7));
        resultList.add(new ProcessBack2("CS",0.2,10.2));
        resultList.add(new ProcessBack2("P1",2.5,10.4));
        resultList.add(new ProcessBack2("CS",0.2,12.9));
        resultList.add(new ProcessBack2("P2",0.5,13.1));

        List<ProcessBack2> rrResult = roundRobin.simulate(processList);
        assertEquals(rrResult, resultList);

        assertNull(roundRobin.simulate(new ArrayList<>()));

    }

}
