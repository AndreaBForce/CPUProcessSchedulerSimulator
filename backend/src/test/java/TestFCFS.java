import org.junit.Test;
import service.FCFS.FCFSAlgorithm;
import service.FCFS.Process;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestFCFS {
    private FCFSAlgorithm fcfsAlgorithm = new FCFSAlgorithm();
    @Test
    public void schedulerFCFSTest(){
        List<Process> processList = new ArrayList<>();
        Process p = new Process("p", 2, 3, 1);
        Process p1 = new Process("p1", 1, 2, 1);
        Process p2 = new Process("p2", 4, 1, 1);
        Process p3 = new Process("p3", 2, 3, 1);
        Process p4 = new Process("p4", 1, 2, 1);
        processList.add(p);
        processList.add(p1);
        processList.add(p2);
        processList.add(p3);
        processList.add(p4);

        List<Process> orderedProcessList = new ArrayList<>();
        orderedProcessList.add(p1);
        orderedProcessList.add(p4);
        orderedProcessList.add(p);
        orderedProcessList.add(p3);
        orderedProcessList.add(p2);

        assertEquals(orderedProcessList,fcfsAlgorithm.schedulerFCFS(processList));
    }
}
