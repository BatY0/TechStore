package AbstractFactory.Components;


import AbstractFactory.Product;

public abstract class Cpu extends Product {
    private SocketType socket;
    private int coreCount;
    private int threadCount;
    private double clockSpeed;

    public Cpu(String name, double price, SocketType socket, int coreCount, int threadCount, double clockSpeed) {
        super(name, price);
        this.socket = socket;
        this.coreCount = coreCount;
        this.threadCount = threadCount;
        this.clockSpeed = clockSpeed;
    }

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

    @Override
    public String getDescription() {
        return String.format("%s CPU, %d cores, %d threads, %.2f GHz, Socket: %s", getName(), coreCount, threadCount, clockSpeed, socket);
    }

}
