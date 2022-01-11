package service.process;

import java.util.Objects;

public abstract class Process {
    String name;
    double burstTime;

    public Process(String name, double burstTime) {
        this.name = name;
        this.burstTime = burstTime;
    }

    public String getName() {
        return name;
    }

    public double getBurstTime() {
        return burstTime;
    }

    @Override
    public String toString() {
        return "\n" + getName() + " bt: "+getBurstTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Process process = (Process) o;
        return Double.compare(process.getBurstTime(), getBurstTime()) == 0 && Objects.equals(getName(), process.getName());
    }
}
