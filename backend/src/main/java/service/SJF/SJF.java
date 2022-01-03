package service.SJF;

import service.IAlgorithm;
import service.process.Process;
import service.process.ProcessBatch;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class SJF implements IAlgorithm {

    private double contextSwitch;

    public SJF(double contextSwitch) {
        this.contextSwitch = contextSwitch;
    }

    @Override
    public List<Process> compute(List<? extends Process> processes){
        if (processes.isEmpty())
            return null;
        List<ProcessBatch> sorted = processes.stream().filter(ProcessBatch.class::isInstance).map(ProcessBatch.class::cast).sorted(Comparator.comparingDouble(ProcessBatch::getArrivalTime)).collect(Collectors.toList());
        List<ProcessBatch> queue = new ArrayList<>();
        List<Process> result = new ArrayList<>();
        double time = 0;
        ProcessBatch exec = null;

        while (!sorted.isEmpty() || !queue.isEmpty()){
            double finalTime = time;
            queue.addAll(sorted.stream().filter(processBatch -> processBatch.getArrivalTime() <= finalTime).collect(Collectors.toList()));
            sorted.removeIf(processBatch -> processBatch.getArrivalTime() <= finalTime);

            if (queue.isEmpty()){
                result.add(new ProcessBatch("SP", new BigDecimal(sorted.get(0).getArrivalTime()-time).setScale(1, RoundingMode.HALF_UP).doubleValue(), new BigDecimal(time).setScale(1, RoundingMode.HALF_UP).doubleValue()));
                time = sorted.get(0).getArrivalTime();
                exec = null;
                continue;
            }else if(contextSwitch > 0 && exec != null){
                result.add(new ProcessBatch("CS", contextSwitch, new BigDecimal(time).setScale(1, RoundingMode.HALF_UP).doubleValue()));
                time += contextSwitch;
            }

            queue.sort(Comparator.comparingDouble(ProcessBatch::getBurstTime));

            exec = queue.remove(0);
            result.add(exec);
            time += exec.getBurstTime();
        }

        System.out.println("Result: "+result);

        return result;
    }
}