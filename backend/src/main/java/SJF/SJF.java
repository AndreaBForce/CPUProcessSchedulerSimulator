package SJF;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class SJF {

    private double contextSwitch;

    public SJF(double contextSwitch) {
        this.contextSwitch = contextSwitch;
    }

    public List<ProcessBack> simulate(List<ProcessBack> processes){

        if (processes.isEmpty())
            return null;
        List<ProcessBack> sorted = processes.stream().sorted(Comparator.comparingDouble(value -> value.arrivalTime)).collect(Collectors.toList());
        List<ProcessBack> queue = new ArrayList<>();
        List<ProcessBack> result = new ArrayList<>();
        double time = 0;
        ProcessBack exec = null;

        while (!sorted.isEmpty() || !queue.isEmpty()){
            double finalTime = time;
            queue.addAll(sorted.stream().filter(processBack -> processBack.arrivalTime <= finalTime).collect(Collectors.toList()));
            sorted.removeIf(processBack -> processBack.arrivalTime <= finalTime);

            if (queue.isEmpty()){
                result.add(new ProcessBack("SP", new BigDecimal(sorted.get(0).arrivalTime-time).setScale(1, RoundingMode.HALF_UP).doubleValue(), new BigDecimal(time).setScale(1, RoundingMode.HALF_UP).doubleValue()));
                time = sorted.get(0).arrivalTime;
                exec = null;
                continue;
            }else if(contextSwitch > 0 && exec != null){
                result.add(new ProcessBack("CS", contextSwitch, new BigDecimal(time).setScale(1, RoundingMode.HALF_UP).doubleValue()));
                time += contextSwitch;
            }

            queue.sort(Comparator.comparingDouble(value -> value.burstTime));

            exec = queue.remove(0);
            result.add(exec);
            time += exec.burstTime;
        }

        System.out.println("Result: "+result);

        return result;
    }

}
