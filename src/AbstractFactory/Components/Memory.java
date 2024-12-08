package AbstractFactory.Components;

import AbstractFactory.Product;


public abstract class Memory extends Product {
    private int capacity;
    private int speed;
    private MemoryType type;

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


    @Override
    public String getDescription() {
        return String.format("%s (Type: %s, Capacity: %dGB, Speed: %dMHz, Price: $%.2f)",
                getName(), type, capacity, speed, getPrice());
    }

}
