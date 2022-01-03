package service.RoundRobin;

import service.IAlgorithm;
import service.process.Process;
import service.process.ProcessBatch;
import service.process.ProcessInteractive;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class RoundRobin implements IAlgorithm {
    private double contextSwitchTime;
    private double quantum;

    public RoundRobin(double contextSwitchTime, double quantum) {
        this.contextSwitchTime = contextSwitchTime;
        this.quantum = quantum;
    }

    public List<Process> compute(List<? extends Process> processes) {
        if (processes.isEmpty())
            return null;
        List<ProcessInteractive> sorted = processes.stream().filter(ProcessInteractive.class::isInstance).map(ProcessInteractive.class::cast).sorted(Comparator.comparingDouble(ProcessInteractive::getArrivalTime)).collect(Collectors.toList());
        Queue<ProcessInteractive> queue = new ArrayDeque<>();
        List<Process> result = new ArrayList<>();
        double time = 0.0;
        ProcessInteractive exec = null;
        ProcessInteractive resultP = null;

        while (!sorted.isEmpty() || !queue.isEmpty()) {
            double finalTime = time;
            queue.addAll(sorted.stream().filter(processInteractive -> processInteractive.getArrivalTime() <= finalTime).collect(Collectors.toList()));
            sorted.removeIf(processInteractive -> processInteractive.getArrivalTime() <= finalTime);

            if (exec != null){
                if (exec.decrementRemainingTime(quantum) > 0) {
                    queue.add(exec);
                }

                if (exec.equals(queue.peek())){
                    resultP.incrementBurstTime(Math.min(exec.getRemainingBurstTime(), quantum));
                    time += Math.min(exec.getRemainingBurstTime(), quantum);
                    queue.poll();
                    continue;
                }else if (!queue.isEmpty()){

                    result.add(new ProcessInteractive("CS", contextSwitchTime, new BigDecimal(time).setScale(1, RoundingMode.HALF_UP).doubleValue()));
                    time += contextSwitchTime;
                }
            }

            if (queue.isEmpty()){
                queue.add(sorted.get(0));
                time = sorted.get(0).getArrivalTime();
                result.add(new ProcessInteractive("",time-finalTime, new BigDecimal(finalTime).setScale(1, RoundingMode.HALF_UP).doubleValue()));
                sorted.remove(0);
            }

            exec = queue.poll();
            resultP = new ProcessInteractive(exec.getName(), Math.min(exec.getRemainingBurstTime(), quantum), new BigDecimal(time).setScale(1, RoundingMode.HALF_UP).doubleValue());
            time += Math.min(exec.getRemainingBurstTime(), quantum);
            result.add(resultP);
        }
        exec.decrementRemainingTime(quantum);
        resultP.incrementBurstTime(Math.min(exec.getRemainingBurstTime(), quantum));

        System.out.println(result);
        return result;
    }
}