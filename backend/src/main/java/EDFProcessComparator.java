import java.util.Comparator;

public class EDFProcessComparator implements Comparator<ProcessBackend> {
    @Override
    public int compare(ProcessBackend o1, ProcessBackend o2) {
        if(o1.getPeriodicity()<o2.getPeriodicity()) {
            return -1;
        }else if(o1.getPeriodicity()>o2.getPeriodicity()){
            return 1;
        }else{
            return 0;
        }
    }
}
