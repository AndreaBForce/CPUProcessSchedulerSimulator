package service.process;

public class ProcessPriority extends Process{
    private final int priority;
    private double remainingBurstTime;

    public ProcessPriority(String name, double burstTime, int priority) {
        super(name, burstTime);
        this.priority = priority;
        this.remainingBurstTime = burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public double getRemainingBurstTime() {
        return remainingBurstTime;
    }

    public double decrementRemainingTime(double quantum){
        this.remainingBurstTime -= Math.min(remainingBurstTime, quantum);
        return remainingBurstTime;
    }

    @Override
    public String toString() {
        return super.toString() + " priority: " + getPriority();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProcessPriority that = (ProcessPriority) o;
        return getPriority() == that.getPriority();
    }
}
