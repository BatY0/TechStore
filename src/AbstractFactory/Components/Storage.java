package AbstractFactory.Components;

import AbstractFactory.Product;


public abstract class Storage extends Product {
    private int capacity;
    private StorageType storageType;

    public Storage(String model, double price, int capacity, StorageType storageType) {
        super(model, price);
        this.capacity = capacity;
        this.storageType = storageType;
    }

    public int getCapacity() {
        return capacity;
    }

    public StorageType getStorageType() {
        return storageType;
    }


    @Override
    public String getDescription() {
        return String.format("%s (Capacity: %dGB, Storage Type: %s, Price: $%.2f)",
                getName(), capacity, storageType, getPrice());
    }
}
