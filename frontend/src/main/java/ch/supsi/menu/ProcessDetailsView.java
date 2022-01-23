package ch.supsi.menu;

import javafx.scene.control.Label;

import java.util.ResourceBundle;

public class ProcessDetailsView {
    private Label processName;
    private Label tmpArrivalTime;
    private Label tmpBurstTime;
    private Label tmpPriority;
    private Label valueArrival;
    private Label valueBurst;
    private Label valuePriority;

    public ProcessDetailsView(ch.supsi.Process process) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/stringhe");
        this.processName = new Label(process.getName());
        this.tmpArrivalTime = new Label(resourceBundle.getString("labelEditArrivalTime.text"));
        this.tmpBurstTime = new Label(resourceBundle.getString("labelEditBurstTime.text"));
        this.tmpPriority = new Label(resourceBundle.getString("labelEditPriority.text"));
        this.valueArrival = new Label(String.valueOf(process.getArrivalTime()));
        this.valueBurst = new Label(String.valueOf(process.getBurstTime()));
        this.valuePriority = new Label(String.valueOf(process.getPriority()));
    }

    public Label getProcessName() {
        return processName;
    }

    public Label getTmpArrivalTime() {
        return tmpArrivalTime;
    }

    public Label getTmpBurstTime() {
        return tmpBurstTime;
    }

    public Label getTmpPriority() {
        return tmpPriority;
    }

    public Label getValueArrival() {
        return valueArrival;
    }

    public Label getValueBurst() {
        return valueBurst;
    }

    public Label getValuePriority() {
        return valuePriority;
    }
}
