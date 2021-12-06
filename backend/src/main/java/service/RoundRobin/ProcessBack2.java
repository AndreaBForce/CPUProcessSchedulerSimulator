package service.RoundRobin;

import java.util.Objects;

public class ProcessBack2 {
    String name;
    double burstTime;
    double arrivalTime;
    double remainingBurstTime;


    public ProcessBack2(String name, double burstTime, double arrivalTime) {
        this.name = name;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.remainingBurstTime = burstTime;
    }

    public double decrementRemainingTime(double quantum){
        this.remainingBurstTime -= Math.min(remainingBurstTime, quantum);
        return remainingBurstTime;
    }

    public void incrementBurstTime(double burstTime) {
        this.burstTime += burstTime;
    }

    @Override
    public String toString() {
        return "\n" + name + " bt: "+ burstTime + " at: "+arrivalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessBack2 that = (ProcessBack2) o;
        return Double.compare(that.burstTime, burstTime) == 0 && Double.compare(that.arrivalTime, arrivalTime) == 0 && Objects.equals(name, that.name);
    }
}
