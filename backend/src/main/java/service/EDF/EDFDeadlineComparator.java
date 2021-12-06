package service.EDF;

import java.util.Comparator;

public class EDFDeadlineComparator implements Comparator<ProcessBackend> {
    @Override
    public int compare(ProcessBackend o1, ProcessBackend o2) {
        if(o1.getNextDeadline()<o2.getNextDeadline()) {
            return -1;
        }else if(o1.getNextDeadline()>o2.getNextDeadline()){
            return 1;
        }else{
            return 0;
        }
    }
}