package service.RMA;

import java.util.ArrayList;

public class MainTestRMA {
    public static void main(String[] args) {
        ProcessBackend pA = new ProcessBackend("A",20,3);
        ProcessBackend pB = new ProcessBackend("B",5,1);
        ProcessBackend pC = new ProcessBackend("C",14,4);


        ArrayList<ProcessBackend> listaProcessi = new ArrayList<>();
        listaProcessi.add(pA);
        listaProcessi.add(pB);
        listaProcessi.add(pC);

        RMSScheduler rms = new RMSScheduler(listaProcessi);

        rms.compute();

        for (ProcessBackend p: rms.getSimulationArray()) {
            System.out.println(p);
        }
    }
}
