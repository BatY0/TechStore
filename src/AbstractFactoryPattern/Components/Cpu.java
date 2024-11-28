package AbstractFactoryPattern.Components;


import AbstractFactoryPattern.Product;

public abstract class Cpu extends Product {
    private SocketType socket;
    private int coreCount;
    private int threadCount;
    private double clockSpeed;

    // Constructor to initialize common CPU properties
    public Cpu(String description, double price, SocketType socket, int coreCount, int threadCount, double clockSpeed) {
        super(description, price);
        this.socket = socket;
        this.coreCount = coreCount;
        this.threadCount = threadCount;
        this.clockSpeed = clockSpeed;
    }

    // Getters for CPU-specific properties
    public SocketType getSocket() {
        return socket;
    }

    public int getCoreCount() {
        return coreCount;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public double getClockSpeed() {
        return clockSpeed;
    }

    // Abstract method to get the model (to be implemented by subclasses)
    public abstract String getModel();

    // Concrete method to return the full description of the CPU
    @Override
    public String getDescription() {
        return String.format("%s CPU, %d cores, %d threads, %.2f GHz, Socket: %s", getModel(), coreCount, threadCount, clockSpeed, socket);
    }

}
