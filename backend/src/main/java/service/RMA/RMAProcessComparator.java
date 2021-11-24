package service.RMA;

import java.util.Comparator;

public class RMAProcessComparator implements Comparator<ProcessBackend> {
    @Override
    public int compare(ProcessBackend o1, ProcessBackend o2) {
        if(o1.getPeriod()<o2.getPeriod()) {
            return -1;
        }else if(o1.getPeriod()>o2.getPeriod()){
            return 1;
        }else{
            return 0;
        }
    }
}