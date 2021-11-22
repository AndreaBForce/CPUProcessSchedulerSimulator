import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EDF {
    private ArrayList<ProcessBackend> arrayProcess;
    private double utilizationBound;
    private boolean isSchedulable;
    private int numberOfCores = 1;

    void initList(ArrayList<ProcessBackend> processList){
        arrayProcess = processList;
        utilizationBound = 0;
    }

    void computeUtilizationBound(){
        for (ProcessBackend p:arrayProcess) {
            utilizationBound = utilizationBound + (p.getPeriodicity()/p.getExecutionTime());
        }

        //TODO CHECK INTERMEDIO PER DIRE CHE NON E' SCHEDULABILE
        if(utilizationBound <= numberOfCores){
            isSchedulable = true;
        }else{
            isSchedulable = false;
        }
    }

    void sortByDeadLine(){
        Collections.sort(arrayProcess,new EDFProcessComparator());
    }

    void EDFCompute(){
        sortByDeadLine();
    }


}
