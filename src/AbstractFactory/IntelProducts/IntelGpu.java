package AbstractFactory.IntelProducts;

import AbstractFactory.Components.Gpu;

public class IntelGpu extends Gpu {

        public IntelGpu(String model, int memorySize, double clockSpeed, double price) {
            super("Intel "+model, price, memorySize, clockSpeed);
        }

        @Override
        public String getBrand() {
            return "Intel";
        }

}
