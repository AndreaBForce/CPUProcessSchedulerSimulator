public class ProcessBack {
    String name;
    int burstTime;
    int arrivalTime;


    public ProcessBack(String name, int burstTime, int arrivalTime) {
        this.name = name;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return name;
    }
}
