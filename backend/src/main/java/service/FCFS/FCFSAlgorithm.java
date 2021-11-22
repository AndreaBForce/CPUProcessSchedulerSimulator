package service.FCFS;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FCFSAlgorithm {

    public List<Process> schedulerFCFS(List<Process> processList){
        Stream<Process> newList = processList.stream().sorted(Comparator.comparing(Process::getArrivalTime));
        return newList.collect(Collectors.toList());
    }
}
