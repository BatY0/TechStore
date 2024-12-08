package AbstractFactory.Components;


import AbstractFactory.Product;

public abstract class Gpu extends Product {
    private int memorySize;
    private double clockSpeed;

    public Gpu(String name, double price, int memorySize, double clockSpeed) {
        super(name, price);
        this.memorySize = memorySize;
        this.clockSpeed = clockSpeed;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public double getClockSpeed() {
        return clockSpeed;
    }

    @Override
    public String getDescription() {
        return String.format("%s GPU, %d GB VRAM, %.2f GHz Clock Speed, Price: $%.2f",
                getName(), memorySize, clockSpeed, getPrice());
    }
}
