package AbstractFactoryPattern.CorsairProducts;

import AbstractFactoryPattern.Components.Memory;
import AbstractFactoryPattern.Components.MemoryType;

public class CorsairMemory extends Memory {

    public CorsairMemory(String model, double price, int capacity, int speed, MemoryType type) {
        super("Corsair " + model, price, capacity, speed, type);
    }

    @Override
    public String getBrand() {
        return "Corsair";
    }

}