package service.EDF;

import java.util.ArrayList;

public class MainTest {
    public static void main(String[] args) {
        ProcessBackend pA = new ProcessBackend("A",6,3);
        ProcessBackend pB = new ProcessBackend("B",8,3);
        ProcessBackend pC = new ProcessBackend("C",10,1);

        ArrayList<ProcessBackend> listaProcessi = new ArrayList<>();
        listaProcessi.add(pA);
        listaProcessi.add(pB);
        listaProcessi.add(pC);

        EDFScheduler edf = new EDFScheduler(listaProcessi);

        edf.compute();

        for (ProcessBackend p: edf.getSimulationArray()) {
            System.out.println(p);
        }

    }
}
