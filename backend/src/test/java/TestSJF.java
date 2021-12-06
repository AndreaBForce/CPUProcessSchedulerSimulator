import org.junit.Test;
import service.SJF.ProcessBack;
import service.SJF.SJF;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TestSJF {

    @Test
    public void testSimulate(){
        List<ProcessBack> process = new ArrayList<>();
        List<ProcessBack> resultList = new ArrayList<>();

        SJF sjf = new SJF(0.2);

        ProcessBack p1 = new ProcessBack("P1",6, 11);
        ProcessBack p2 = new ProcessBack("P2",2, 5);
        ProcessBack p3 = new ProcessBack("P3",8, 11);
        ProcessBack p4 = new ProcessBack("P4",3, 1);
        ProcessBack p5 = new ProcessBack("P5",4, 4);

        process.add(p1);
        process.add(p2);
        process.add(p3);
        process.add(p4);
        process.add(p5);

        resultList.add(new ProcessBack("SP", 1,0));
        resultList.add(new ProcessBack("P4", 3,1));
        resultList.add(new ProcessBack("CS", 0.2,4));
        resultList.add(new ProcessBack("P5", 4,4));
        resultList.add(new ProcessBack("CS", 0.2,8.2));
        resultList.add(new ProcessBack("P2", 2.0,5.0));
        resultList.add(new ProcessBack("SP", 0.6,10.4));
        resultList.add(new ProcessBack("P1", 6,11));
        resultList.add(new ProcessBack("CS", 0.2,17));
        resultList.add(new ProcessBack("P3", 8,11));

        assertEquals(sjf.simulate(process), resultList);
        assertNull(sjf.simulate(new ArrayList<>()));
    }
}
