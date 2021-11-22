public class ProcessBackend {
    private double executionTime;
    private int periodicity;

    public ProcessBackend(double executionTime, int periodicity) {
        this.executionTime = executionTime;
        this.periodicity = periodicity;

        //TODO QUESTO CHECK VA FATTO SULLA LABEL TIPO

    }

    public double getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(double executionTime) {
        this.executionTime = executionTime;
    }

    public int getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(int periodicity) {
        this.periodicity = periodicity;
    }

}
