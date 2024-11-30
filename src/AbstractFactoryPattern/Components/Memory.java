package AbstractFactoryPattern.Components;

import AbstractFactoryPattern.Product;


public abstract class Memory extends Product {
    private int capacity;    // Capacity in GB
    private int speed;       // Speed in MHz
    private MemoryType type; // Memory type as an enum (DDR4, DDR5)

    public Memory(String model, double price, int capacity, int speed, MemoryType type) {
        super(model, price);
        this.capacity = capacity;
        this.speed = speed;
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSpeed() {
        return speed;
    }

    public MemoryType getType() {
        return type;
    }

    // Abstract method to enforce model retrieval in subclasses
    public abstract String getModel();

    @Override
    public String getDescription() {
        return String.format("%s (Type: %s, Capacity: %dGB, Speed: %dMHz, Price: $%.2f)",
                getName(), type, capacity, speed, getPrice());
    }

}
