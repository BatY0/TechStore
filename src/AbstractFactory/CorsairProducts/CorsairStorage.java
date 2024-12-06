package AbstractFactory.CorsairProducts;

import AbstractFactory.Components.Storage;
import AbstractFactory.Components.StorageType;

public class CorsairStorage extends Storage {
    public CorsairStorage(String name, double price, int capacity, StorageType storageType) {
        super("Corsair "+name, price, capacity, storageType);
    }

    @Override
    public String getBrand() {
        return "Corsair";
    }
}
