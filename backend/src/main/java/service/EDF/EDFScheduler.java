package service.EDF;

import service.IAlgorithm;
import service.process.Process;
import service.process.ProcessRealTime;
import service.process.ProcessState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EDFScheduler implements IAlgorithm {

    public EDFScheduler() {}

    public boolean isSchedulable(List<ProcessRealTime> processes){
        double computationBound = 0;
        for (ProcessRealTime p: processes) {
            p.setState(ProcessState.READY);
            p.setNextDeadline((int) p.getPeriod());
            computationBound = computationBound + (p.getBurstTime()/p.getPeriod());
        }

        return computationBound <= 1;
    }

    @Override
    public List<Process> compute(List<? extends Process> processes){
        List<Process> resultList = new ArrayList<>();
        List<ProcessRealTime> simulationList = processes.stream().filter(ProcessRealTime.class::isInstance).map(ProcessRealTime.class::cast).collect(Collectors.toList());
        int TIME_PERIOD = 150;
        boolean someoneRunning = false;
        int nServed = 0;

        simulationList.sort(new EDFDeadlineComparator());

        if(isSchedulable(simulationList)){
            for(int time=0;time<TIME_PERIOD;time++){

                for (ProcessRealTime p: simulationList) {
                    if(p.getState() == ProcessState.READY && !someoneRunning){
                        resultList.add(p);
                        p.setTimePassed(time);
                        p.setNextDeadline(time + (int)p.getPeriod());
                        p.setState(ProcessState.RUNNING);
                        someoneRunning = true;
                    }else if(time == (p.getTimePassed()+p.getBurstTime())){
                        p.setState(ProcessState.SERVED);
                        nServed++;
                        someoneRunning = false;
                    }

                    if(p.getNextDeadline() == time && p.getState() == ProcessState.SERVED){
                        p.setState(ProcessState.READY);
                        nServed--;
                    }

                    if(nServed == simulationList.size()){
                        resultList.add(new ProcessRealTime("Empty",-99999,((float)1/simulationList.size())));
                    }
                }

                simulationList.sort(new EDFDeadlineComparator());
            }
        }else{
            System.out.println("Not schedulable... \nShutdown");
        }

        return resultList;
    }
}
