package AbstractFactory.CorsairProducts;

import AbstractFactory.Components.CoolingType;
import AbstractFactory.Components.CpuCooler;

public class CorsairCooler extends CpuCooler {
    public CorsairCooler(String name, double price, CoolingType coolingType, int fanSpeed, double noiseLevel, String size) {
        super("Corsair "+name, price, coolingType, fanSpeed, noiseLevel, size);
    }

    @Override
    public String getBrand() {
        return "Corsair";
    }
}
