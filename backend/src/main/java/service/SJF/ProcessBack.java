package service.SJF;

import java.util.Objects;

public class ProcessBack {
    String name;
    double burstTime;
    double arrivalTime;


    public ProcessBack(String name, double burstTime, double arrivalTime) {
        this.name = name;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "\n" + name + " bt: "+burstTime + " at: "+arrivalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessBack that = (ProcessBack) o;
        return Double.compare(that.burstTime, burstTime) == 0 && Double.compare(that.arrivalTime, arrivalTime) == 0 && Objects.equals(name, that.name);
    }
}
