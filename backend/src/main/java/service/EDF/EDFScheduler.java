package service.EDF;

import java.util.ArrayList;
import java.util.Collections;

public class EDFScheduler {
    private ArrayList<ProcessBackend> processArray;

    private ArrayList<ProcessBackend> simulationArray;

    public EDFScheduler(ArrayList<ProcessBackend> processArray) {
        this.processArray = processArray;
        this.simulationArray = new ArrayList<>();
    }

    boolean isSchedulable(){
        float computationBound = 0;
        for (ProcessBackend p: processArray) {
            p.setState(ProcessState.READY);
            p.setNextDeadline((int) p.getPeriod());
            computationBound = computationBound + (p.getExecutionTime()/p.getPeriod());
        }

        if(computationBound<= 1){
            return true;
        }else{
            return false;
        }
    }

    void compute(){
        int TIME_PERIOD = 150;
        boolean someoneRunning = false;
        int nServed = 0;

        Collections.sort(processArray,new EDFDeadlineComparator());

        if(isSchedulable()){
            for(int time=0;time<TIME_PERIOD;time++){

                for (ProcessBackend p: processArray) {
                    if(p.getState() == ProcessState.READY && !someoneRunning){
                        simulationArray.add(p);
                        p.setTimePassed(time);
                        p.setNextDeadline(time + (int)p.getPeriod());
                        p.setState(ProcessState.RUNNING);
                        someoneRunning = true;
                    }else if(time == (p.getTimePassed()+p.getExecutionTime())){
                        p.setState(ProcessState.SERVED);
                        nServed++;
                        someoneRunning = false;
                    }

                    if(p.getNextDeadline() == time && p.getState() == ProcessState.SERVED){
                        p.setState(ProcessState.READY);
                        nServed--;
                    }

                    if(nServed == processArray.size()){
                        simulationArray.add(new ProcessBackend("Empty",-99999,((float)1/processArray.size())));
                    }
                }

                //Collections.sort(processArray,new EDFDeadlineComparator());
            }
        }else{
            System.out.println("Not schedulable... \nShutdown");
        }

    }

    public ArrayList<ProcessBackend> getSimulationArray() {
        return simulationArray;
    }

}
