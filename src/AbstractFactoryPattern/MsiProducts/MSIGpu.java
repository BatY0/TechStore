package AbstractFactoryPattern.MsiProducts;

import AbstractFactoryPattern.Components.Gpu;

public class MSIGpu extends Gpu {
    public MSIGpu(String model, double price, int memorySize, double clockSpeed) {
        super("MSI " + model, price, memorySize, clockSpeed);
    }

    @Override
    public String getBrand() {
        return "MSI";
    }

}


