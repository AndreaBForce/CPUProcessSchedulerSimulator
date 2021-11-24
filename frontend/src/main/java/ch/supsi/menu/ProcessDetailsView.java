package ch.supsi.menu;

import javafx.scene.control.Label;

public class ProcessDetailsView {
    private Label processName;
    private Label tmpArrivalTime;
    private Label tmpBurstTime;
    private Label tmpPriority;
    private Label valueArrival;
    private Label valueBurst;
    private Label valuePriority;

    public ProcessDetailsView(ch.supsi.Process process) {
        this.processName = new Label(process.getName());
        this.tmpArrivalTime = new Label("Arrival time: ");
        this.tmpBurstTime = new Label("Burst time: ");
        this.tmpPriority = new Label("Priority: ");
        this.valueArrival = new Label(String.valueOf(process.getArrivalTime()));
        this.valueBurst = new Label(String.valueOf(process.getBurstTime()));
        this.valuePriority = new Label(String.valueOf(process.getPriority()));
    }

    public Label getProcessName() {
        return processName;
    }

    public void setProcessName(Label processName) {
        this.processName = processName;
    }

    public Label getTmpArrivalTime() {
        return tmpArrivalTime;
    }

    public void setTmpArrivalTime(Label tmpArrivalTime) {
        this.tmpArrivalTime = tmpArrivalTime;
    }

    public Label getTmpBurstTime() {
        return tmpBurstTime;
    }

    public void setTmpBurstTime(Label tmpBurstTime) {
        this.tmpBurstTime = tmpBurstTime;
    }

    public Label getTmpPriority() {
        return tmpPriority;
    }

    public void setTmpPriority(Label tmpPriority) {
        this.tmpPriority = tmpPriority;
    }

    public Label getValueArrival() {
        return valueArrival;
    }

    public void setValueArrival(Label valueArrival) {
        this.valueArrival = valueArrival;
    }

    public Label getValueBurst() {
        return valueBurst;
    }

    public void setValueBurst(Label valueBurst) {
        this.valueBurst = valueBurst;
    }

    public Label getValuePriority() {
        return valuePriority;
    }

    public void setValuePriority(Label valuePriority) {
        this.valuePriority = valuePriority;
    }
}
