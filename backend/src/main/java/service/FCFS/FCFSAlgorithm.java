package service.FCFS;

import service.IAlgorithm;
import service.process.Process;
import service.process.ProcessBatch;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FCFSAlgorithm implements IAlgorithm {
    private double contextSwitch;

    public FCFSAlgorithm(double contextSwitch) {
        this.contextSwitch = contextSwitch;
    }

    @Override
    public List<Process> compute(List<? extends Process> processes) {
        List<ProcessBatch> sortedList = processes.stream().filter(ProcessBatch.class::isInstance).map(ProcessBatch.class::cast).sorted(Comparator.comparing(ProcessBatch::getArrivalTime)).collect(Collectors.toList());
        List<ProcessBatch> queue = new ArrayList<>();
        List<Process> result = new ArrayList<>();
        double time = 0;
        ProcessBatch exec = null;

        while (!sortedList.isEmpty() || !queue.isEmpty()) {
            double finalTime = time;
            queue.addAll(sortedList.stream().filter(processBatch -> processBatch.getArrivalTime() <= finalTime).collect(Collectors.toList()));
            sortedList.removeIf(processBatch -> processBatch.getArrivalTime() <= finalTime);

            queue = queue.stream().sorted(Comparator.comparing(ProcessBatch::getArrivalTime)).collect(Collectors.toList());

            if (queue.isEmpty()) {
                result.add(new ProcessBatch("SP", new BigDecimal(sortedList.get(0).getArrivalTime() - time).setScale(1, RoundingMode.HALF_UP).doubleValue(), new BigDecimal(time).setScale(1, RoundingMode.HALF_UP).doubleValue()));
                time = sortedList.get(0).getArrivalTime();
                exec = null;
                continue;
            } else if (contextSwitch > 0 && exec != null) {
                result.add(new ProcessBatch("CS", contextSwitch, new BigDecimal(time).setScale(1, RoundingMode.HALF_UP).doubleValue()));
                time += contextSwitch;
            }
            exec = queue.remove(0);
            result.add(exec);
            time += exec.getBurstTime();
        }

        return result;
    }

}
