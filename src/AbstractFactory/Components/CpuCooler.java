package AbstractFactory.Components;

import AbstractFactory.Product;

public abstract class CpuCooler extends Product {

    private CoolingType coolingType;
    private int fanSpeed;
    private double noiseLevel;
    private String size;

    // Constructor to initialize common CpuCooler properties
    public CpuCooler(String name, double price, CoolingType coolingType, int fanSpeed, double noiseLevel, String size) {
        super(name, price);
        this.coolingType = coolingType;
        this.fanSpeed = fanSpeed;
        this.noiseLevel = noiseLevel;
        this.size = size;
    }

    // Getter methods for common CpuCooler properties
    public CoolingType getCoolingType() {
        return coolingType;
    }

    public int getFanSpeed() {
        return fanSpeed;
    }

    public double getNoiseLevel() {
        return noiseLevel;
    }

    public String getSize() {
        return size;
    }


    // Default description for CpuCooler
    @Override
    public String getDescription() {
        return String.format("%s (Cooling Type: %s, Fan Speed: %d RPM, Noise Level: %.2f dB, Size: %s, Price: $%.2f)",
                getName(), coolingType, fanSpeed, noiseLevel, size, getPrice());
    }
}
