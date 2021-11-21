import java.util.*;
import java.util.stream.Collectors;

public class SJF {

    static public List<ProcessBack> simulate(List<ProcessBack> process){
        List<ProcessBack> sorted = process.stream().sorted(Comparator.comparingInt(value -> value.arrivalTime)).collect(Collectors.toList());
        List<ProcessBack> queue = new ArrayList<>();
        List<ProcessBack> result = new ArrayList<>();
        int time = 0;

        while (!sorted.isEmpty() || !queue.isEmpty()){
            int finalTime = time;
            queue.addAll(sorted.stream().filter(processBack -> processBack.arrivalTime <= finalTime).collect(Collectors.toList()));
            sorted.removeIf(processBack -> processBack.arrivalTime <= finalTime);

            if (queue.isEmpty()){
//                result.add(new ProcessBack("SP",sorted.get(0).arrivalTime-time, time));
                time = sorted.get(0).arrivalTime;
                continue;
            }

            queue.sort(Comparator.comparingInt(value -> value.burstTime));

            ProcessBack toAdd = queue.remove(0);
            result.add(toAdd);
            time += toAdd.burstTime;
        }

        System.out.println("Result: "+result);

        return result;
    }

}
