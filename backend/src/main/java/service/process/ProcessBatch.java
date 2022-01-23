package service.process;

public class ProcessBatch extends Process{
    private double arrivalTime;


    public ProcessBatch(String name, double burstTime, double arrivalTime) {
        super(name, burstTime);
        this.arrivalTime = arrivalTime;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBurstTime(double burstTime) {
        this.burstTime = burstTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return super.toString() + " at: "+getArrivalTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProcessBatch that = (ProcessBatch) o;
        return Double.compare(that.getArrivalTime(), getArrivalTime()) == 0;
    }
}
