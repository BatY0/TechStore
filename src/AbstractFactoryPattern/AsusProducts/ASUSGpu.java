package AbstractFactoryPattern.AsusProducts;

import AbstractFactoryPattern.Components.Gpu;
import AbstractFactoryPattern.MsiProducts.MSIGpu;


public class ASUSGpu extends Gpu {
    public ASUSGpu(String model, double price, int memorySize, double clockSpeed) {
        super("Asus "+model, price, memorySize, clockSpeed);
    }

    @Override
    public String getModel() {
        return getName();
    }


}
