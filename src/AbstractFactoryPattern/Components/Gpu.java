package AbstractFactoryPattern.Components;


import AbstractFactoryPattern.Product;

public abstract class Gpu extends Product {
    private int memorySize;
    private double clockSpeed;

    // Constructor to initialize the GPU's memory size and clock speed
    public Gpu(String name, double price, int memorySize, double clockSpeed) {
        super(name, price);
        this.memorySize = memorySize;
        this.clockSpeed = clockSpeed;
    }

    // Getters for memory size and clock speed
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
