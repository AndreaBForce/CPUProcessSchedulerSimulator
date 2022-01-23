package service.RMA;

import service.process.ProcessRealTime;

import java.util.Comparator;

public class RMAProcessComparator implements Comparator<ProcessRealTime> {
    @Override
    public int compare(ProcessRealTime o1, ProcessRealTime o2) {
        if(o1.getPeriod()<o2.getPeriod()) {
            return -1;
        }else if(o1.getPeriod()>o2.getPeriod()){
            return 1;
        }else{
            return 0;
        }
    }
}