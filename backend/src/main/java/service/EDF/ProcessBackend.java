package service.EDF;

public class ProcessBackend {
    private String name;
    private float period;
    private float executionTime;
    private ProcessState state;

    private int timePassed;
    private int nextDeadline;


    public ProcessBackend(String name,float period, float executionTime) {
        this.name = name;
        this.period = period;
        this.executionTime = executionTime;
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

    public float getPeriod() {
        return period;
    }

    public void setPeriod(float period) {
        this.period = period;
    }

    public float getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(float executionTime) {
        this.executionTime = executionTime;
    }

    public ProcessState getState() {
        return state;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Process{" +
                "name='" + name + '\'' +
                ", period=" + period +
                ", executionTime=" + executionTime +
                ", state=" + state +
                ", timePassed=" + timePassed +
                ", nextDeadline=" + nextDeadline +
                '}';
    }
}
