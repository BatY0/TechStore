package AbstractFactory.AsusProducts;

import AbstractFactory.Components.CoolingType;
import AbstractFactory.Components.CpuCooler;

public class AsusCpuCooler extends CpuCooler {
    public AsusCpuCooler(String name, double price, CoolingType coolingType, int fanSpeed, double noiseLevel, String size) {
        super("Asus "+name, price, coolingType, fanSpeed, noiseLevel, size);
    }

    @Override
    public String getBrand() {
        return "Asus";
    }
}
