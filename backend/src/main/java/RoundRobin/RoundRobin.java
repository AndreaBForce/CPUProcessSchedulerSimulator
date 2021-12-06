package RoundRobin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class RoundRobin {
    private double contextSwitchTime;
    private double quantum;

    public RoundRobin(double contextSwitchTime, double quantum) {
        this.contextSwitchTime = contextSwitchTime;
        this.quantum = quantum;
    }

    public List<ProcessBack2> simulate(List<ProcessBack2> processList) {
        if (processList.isEmpty())
            return null;
        List<ProcessBack2> sorted = processList.stream().sorted(Comparator.comparingDouble(value -> value.arrivalTime)).collect(Collectors.toList());
        Queue<ProcessBack2> queue = new ArrayDeque<>();
        List<ProcessBack2> result = new ArrayList<>();
        double time = 0.0;
        ProcessBack2 exec = null;
        ProcessBack2 resultP = null;

        while (!sorted.isEmpty() || !queue.isEmpty()) {
            double finalTime = time;
            queue.addAll(sorted.stream().filter(processBack2 -> processBack2.arrivalTime <= finalTime).collect(Collectors.toList()));
            sorted.removeIf(processBack2 -> processBack2.arrivalTime <= finalTime);

            if (exec != null){
                if (exec.decrementRemainingTime(quantum) > 0) {
                    queue.add(exec);
                }

                if (exec.equals(queue.peek())){
                    resultP.incrementBurstTime(Math.min(exec.remainingBurstTime, quantum));
                    time += Math.min(exec.remainingBurstTime, quantum);
                    queue.poll();
                    continue;
                }else if (!queue.isEmpty()){

                    result.add(new ProcessBack2("CS", contextSwitchTime, new BigDecimal(time).setScale(1, RoundingMode.HALF_UP).doubleValue()));
                    time += contextSwitchTime;
                }
            }

            if (queue.isEmpty()){
                queue.add(sorted.get(0));
                time = sorted.get(0).arrivalTime;
                result.add(new ProcessBack2("",time-finalTime, new BigDecimal(finalTime).setScale(1, RoundingMode.HALF_UP).doubleValue()));
                sorted.remove(0);
            }

            exec = queue.poll();
            resultP = new ProcessBack2(exec.name, Math.min(exec.remainingBurstTime, quantum), new BigDecimal(time).setScale(1, RoundingMode.HALF_UP).doubleValue());
            time += Math.min(exec.remainingBurstTime, quantum);
            result.add(resultP);
        }
        exec.decrementRemainingTime(quantum);
        resultP.incrementBurstTime(Math.min(exec.remainingBurstTime, quantum));

        System.out.println(result);
        return result;
    }
}