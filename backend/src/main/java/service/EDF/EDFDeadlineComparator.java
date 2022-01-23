package service.EDF;

import service.process.ProcessRealTime;

import java.util.Comparator;

public class EDFDeadlineComparator implements Comparator<ProcessRealTime> {
    @Override
    public int compare(ProcessRealTime o1, ProcessRealTime o2) {
        if(o1.getNextDeadline()<o2.getNextDeadline()) {
            return -1;
        }else if(o1.getNextDeadline()>o2.getNextDeadline()){
            return 1;
        }else{
            return 0;
        }
    }
}