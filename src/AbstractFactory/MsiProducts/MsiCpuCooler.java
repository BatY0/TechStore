package AbstractFactory.MsiProducts;

import AbstractFactory.Components.CpuCooler;


public class MsiCpuCooler extends CpuCooler {
    public MsiCpuCooler(String model, double price, CoolingType coolingType, int fanSpeed, double noiseLevel, double coolingCapacity, String size) {
        super("Msi "+model, price, coolingType, fanSpeed, noiseLevel, size);
    }
    public String getBrand() {
        return "Msi";
    }
}
