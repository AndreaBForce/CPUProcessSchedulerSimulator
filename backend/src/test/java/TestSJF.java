import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestSJF {

    @Test
    public void testSimulate(){
        List<ProcessBack> process = new ArrayList<>();

        ProcessBack p1 = new ProcessBack("P1",6, 2);
        ProcessBack p2 = new ProcessBack("P2",2, 5);
        ProcessBack p3 = new ProcessBack("P3",8, 1);
        ProcessBack p4 = new ProcessBack("P4",3, 1);
        ProcessBack p5 = new ProcessBack("P5",4, 4);


        process.add(p1);
        process.add(p2);
        process.add(p3);
        process.add(p4);
        process.add(p5);

        List<ProcessBack> afterSJF = SJF.simulate(process);


    }
}
