package service.Lottery;

import service.IAlgorithm;
import service.process.Process;
import service.process.ProcessPriority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Lottery implements IAlgorithm {

    private final double quantum;

    public Lottery(double quantum) {
        this.quantum = quantum;
    }

    @Override
    public List<Process> compute(List<? extends Process> processes) {
        if (processes.isEmpty())
            return Collections.emptyList();
        List<ProcessPriority> simulationList = processes.stream()
                .filter(ProcessPriority.class::isInstance)
                .map(ProcessPriority.class::cast)
                .sorted(Comparator.comparingInt(ProcessPriority::getPriority).reversed())
                .collect(Collectors.toList());
        List<Process> resultList = new ArrayList<>();

        while (!simulationList.isEmpty()){

            ProcessPriority exec = numberExtraction(simulationList);

            resultList.add(new ProcessPriority(exec.getName(), Math.min(exec.getRemainingBurstTime(), quantum), exec.getPriority()));

            if (exec.decrementRemainingTime(quantum) == 0){
                simulationList.remove(exec);
            }
        }

        System.out.println(simulationList);
        System.out.println(resultList);

        return resultList;
    }

    private ProcessPriority numberExtraction(List<ProcessPriority> list){
        int max = list.stream().mapToInt(ProcessPriority::getPriority).sum();

        int random = ThreadLocalRandom.current().nextInt(1,max+1);

        int actualNum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i == 0){
                if (random >= 1 && random <= list.get(i).getPriority())
                    return list.get(i);

                actualNum = list.get(i).getPriority();
            }else {
                int rangeBase = actualNum+1;

                if (random >= rangeBase && random <= actualNum+list.get(i).getPriority())
                    return list.get(i);

                actualNum += list.get(i).getPriority();
            }
        }

        return null;
    }
}