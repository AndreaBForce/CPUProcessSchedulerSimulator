package ch.supsi;

import javafx.scene.paint.Color;

public class Process {
    private String name;
    private float burstTime;
    private float arrivalTime;
    private int priority;
    private Color color;

    public Process(String name, float burstTime, float arrivalTime, int priority,Color color) {
        this.name = name;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.color = color;
    }

    public Process(String name, float burstTime, float arrivalTime, Color color) {
        this.name = name;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(float burstTime) {
        this.burstTime = burstTime;
    }

    public float getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(float arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return name;
    }


    //TODO Spostare questo pezzo di codice che converte in esadecimale il colore in una classe adatta
    private String format(double val) {
        String in = Integer.toHexString((int) Math.round(val * 255));
        return in.length() == 1 ? "0" + in : in;
    }

    public String toHexString(Color value) {
        return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()))
                .toUpperCase();
    }
}
