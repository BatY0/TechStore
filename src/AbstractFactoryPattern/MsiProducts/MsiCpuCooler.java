package AbstractFactoryPattern.MsiProducts;

import AbstractFactoryPattern.Components.CpuCooler;
import AbstractFactoryPattern.Product;

public class MsiCpuCooler extends Product implements CpuCooler {

    private CpuCooler.CoolingType coolingType;
    private int fanSpeed;
    private double noiseLevel;
    private double coolingCapacity;
    private String size;

    // Constructor
    public MsiCpuCooler(String name, double price, CoolingType coolingType, int fanSpeed, double noiseLevel, double coolingCapacity, String size) {
        super(name, price);
        this.coolingType = coolingType;
        this.fanSpeed = fanSpeed;
        this.noiseLevel = noiseLevel;
        this.coolingCapacity = coolingCapacity;
        this.size = size;
    }

    // Getter methods for the cooler properties
    @Override
    public CoolingType getCoolingType() {
        return coolingType;
    }

    @Override
    public int getFanSpeed() {
        return fanSpeed;
    }

    @Override
    public double getNoiseLevel() {
        return noiseLevel;
    }


    @Override
    public String getSize() {
        return size;
    }

    // Overriding the getDescription method from Product to include cooler details
    @Override
    public String getDescription() {
        return String.format("%s (Cooling Type: %s, Fan Speed: %d RPM, Noise Level: %.2f dB, Cooling Capacity: %.2f W, Size: %s)",
                super.getDescription(), coolingType, fanSpeed, noiseLevel, coolingCapacity, size);
    }

    // Implementing methods from the CpuCooler interface
    @Override
    public String getModel() {
        return getDescription();  // Can return the description as the model
    }


}
