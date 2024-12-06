package AbstractFactory.MsiProducts;

import AbstractFactory.Components.Storage;
import AbstractFactory.Components.StorageType;

public class MsiStorage extends Storage {
    public MsiStorage(String model, int capacity, StorageType type, double price) {
        super("MSI "+model, price, capacity, type);
    }

    @Override
    public String getBrand() {
        return "MSI";
    }
}
