package service.FCFS;

import service.IAlgorithm;
import service.process.Process;
import service.process.ProcessBatch;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FCFSAlgorithm implements IAlgorithm {

    @Override
    public List<Process> compute(List<? extends Process> processes){
        System.out.println("ciao2");
        Stream<ProcessBatch> newList = processes.stream().filter(ProcessBatch.class::isInstance).map(ProcessBatch.class::cast).sorted(Comparator.comparing( ProcessBatch::getArrivalTime));
        return newList.collect(Collectors.toList());
    }

}
