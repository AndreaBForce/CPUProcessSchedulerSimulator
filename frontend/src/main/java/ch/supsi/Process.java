package ch.supsi;

public class Process {
    String name;

    public Process(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
