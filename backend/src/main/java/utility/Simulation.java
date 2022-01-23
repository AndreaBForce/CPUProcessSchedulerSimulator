package utility;

public class Simulation {
    private String name;
    private String algorithmName;

    public Simulation(String name, String algorithmName) {
        this.name = name;
        this.algorithmName = algorithmName;
    }

    public String getName() {
        return name;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }
}
