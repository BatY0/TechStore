package AbstractFactoryPattern.Components;

import AbstractFactoryPattern.Product;


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

    public abstract String getModel();

    @Override
    public String getDescription() {
        return String.format("%s (Capacity: %dGB, Storage Type: %s, Price: $%.2f)",
                getModel(), capacity, storageType, getPrice());
    }
}
