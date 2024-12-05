package AbstractFactory.MsiProducts;

import AbstractFactory.Components.CoolingType;
import AbstractFactory.Components.CpuCooler;


public class MsiCpuCooler extends CpuCooler {
    public MsiCpuCooler(String name, double price, CoolingType coolingType, int fanSpeed, double noiseLevel, String size) {
        super("Msi "+name, price, coolingType, fanSpeed, noiseLevel, size);
    }
    public String getBrand() {
        return "Msi";
    }
}
