package AbstractFactoryPattern.Components;


import AbstractFactoryPattern.Product;

public abstract class Gpu extends Product {
    private int memorySize;
    private double clockSpeed;

    // Constructor to initialize the GPU's memory size and clock speed
    public Gpu(String description, double price, int memorySize, double clockSpeed) {
        super(description, price);
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

    // Abstract method to get the model name (to be implemented by subclasses)
    public abstract String getModel();

    @Override
    public String getDescription() {
        return String.format("%s GPU, %d GB VRAM, %.2f GHz Clock Speed, Price: $%.2f",
                getModel(), memorySize, clockSpeed, getPrice());
    }
}
