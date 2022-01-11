package service.process;

public class ProcessInteractive extends Process {
    private double arrivalTime;
    private double remainingBurstTime;

    public ProcessInteractive(String name, double burstTime, double arrivalTime) {
        this.name = name;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.remainingBurstTime = burstTime;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public double getRemainingBurstTime() {
        return remainingBurstTime;
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
        if (!super.equals(o)) return false;
        ProcessInteractive that = (ProcessInteractive) o;
        return Double.compare(that.getArrivalTime(), getArrivalTime()) == 0;
    }
}
