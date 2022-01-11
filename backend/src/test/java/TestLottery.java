import org.junit.Test;
import service.Lottery.Lottery;
import service.process.ProcessPriority;

import java.util.ArrayList;
import java.util.List;

public class TestLottery {

    @Test
    public void testCompute(){
        List<ProcessPriority> processes = new ArrayList<>();
        Lottery lottery = new Lottery(2);

        ProcessPriority p1 = new ProcessPriority("P1",5,10);
        ProcessPriority p2 = new ProcessPriority("P2",7,80);
        ProcessPriority p3 = new ProcessPriority("P3",2,10);

        processes.add(p1);
        processes.add(p2);
        processes.add(p3);

        lottery.compute(processes);
    }
}
