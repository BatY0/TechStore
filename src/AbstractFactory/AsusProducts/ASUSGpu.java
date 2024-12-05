package AbstractFactory.AsusProducts;

import AbstractFactory.Components.Gpu;


public class ASUSGpu extends Gpu {
    public ASUSGpu(String model, double price, int memorySize, double clockSpeed) {
        super("Asus "+model, price, memorySize, clockSpeed);
    }

    @Override
    public String getBrand() {
        return "Asus";
    }


}
