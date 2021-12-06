package service.RMA;

import java.util.ArrayList;
import java.util.Collections;

public class RMSScheduler {

    private ArrayList<ProcessBackend> processArray;

    private ArrayList<ProcessBackend> simulationArray;

    public RMSScheduler(ArrayList<ProcessBackend> processArray) {
        this.processArray = processArray;
        simulationArray = new ArrayList<>();
    }


    public boolean isSchedulable(){
        float computationBound = 0;
        for (ProcessBackend p: processArray) {
            p.setState(ProcessState.READY);
            computationBound = computationBound + (p.getExecutionTime()/p.getPeriod());

        }

        if(computationBound<= 0.77){
            return true;
        }else{
            return false;
        }
    }


    void compute(){
        int TIME_PERIOD = 150,prior;
        boolean someoneRunning = false;
        int nServed = 0;
        Collections.sort(processArray,new RMAProcessComparator());


        if(isSchedulable()){

            for(int time=0;time<TIME_PERIOD;time++){

                for (ProcessBackend p: processArray ) {
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

                    if(p.getNextDeadline() == time){
                        p.setState(ProcessState.READY);
                        nServed--;
                    }

                    if(nServed == processArray.size()){
                        simulationArray.add(new ProcessBackend("Empty",-99999,1/processArray.size()));
                    }

                }

            }


        }else{
            System.out.println("Non schedulabile in realtime... \nShutdown");
        }
    }

    public ArrayList<ProcessBackend> getSimulationArray() {
        return simulationArray;
    }

    public void setSimulationArray(ArrayList<ProcessBackend> simulationArray) {
        this.simulationArray = simulationArray;
    }
}

