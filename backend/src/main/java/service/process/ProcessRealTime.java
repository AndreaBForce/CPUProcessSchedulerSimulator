package service.process;

public class ProcessRealTime extends Process {
    private double period;
    private ProcessState state;

    private int timePassed;
    private int nextDeadline;


    public ProcessRealTime(String name, double period, double burstTime) {
        super(name, burstTime);
        this.period = period;
        this.timePassed = -99999;
        this.nextDeadline = -99999;
    }

    public int getNextDeadline() {
        return nextDeadline;
    }

    public void setNextDeadline(int nextDeadline) {
        this.nextDeadline = nextDeadline;
    }

    public int getTimePassed() {
        return timePassed;
    }

    public void setTimePassed(int timePassed) {
        this.timePassed = timePassed;
    }

    public double getPeriod() {
        return period;
    }

    public void setPeriod(double period) {
        this.period = period;
    }

    public void setBurstTime(double burstTime) {
        this.burstTime = burstTime;
    }

    public ProcessState getState() {
        return state;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Process{" +
                "name='" + name + '\'' +
                ", period=" + period +
                ", executionTime=" + burstTime +
                ", state=" + state +
                ", timePassed=" + timePassed +
                ", nextDeadline=" + nextDeadline +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProcessRealTime that = (ProcessRealTime) o;
        return Double.compare(that.getPeriod(), getPeriod()) == 0 && getTimePassed() == that.getTimePassed() && getNextDeadline() == that.getNextDeadline() && getState() == that.getState();
    }
}