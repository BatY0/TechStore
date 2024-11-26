package AbstractFactoryPattern.Components.GpuPackage;

import AbstractFactoryPattern.Product;

public class ASUSGpu extends Product implements Gpu {
    private int memorySize;
    private double clockSpeed;

    public ASUSGpu(String model, double price, int memorySize, double clockSpeed) {
        super("ASUS GPU: " + model, price);
        this.memorySize = memorySize;
        this.clockSpeed = clockSpeed;
    }

    @Override
    public String getModel() {
        return getDescription().replace("ASUS GPU: ", "");
    }

    @Override
    public int getMemorySize() {
        return memorySize;
    }

    @Override
    public double getClockSpeed() {
        return clockSpeed;
    }

    @Override
    public String getFullSpecs() {
        return "Model: " + getModel() + "\n" +
                "Price: $" + getPrice() + "\n" +
                "Memory Size: " + memorySize + "GB\n" +
                "Clock Speed: " + clockSpeed + "MHz\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ASUSGpu asusGpu = (ASUSGpu) obj;
        return getModel().equals(asusGpu.getModel());
    }

    @Override
    public int hashCode() {
        return getModel().hashCode();
    }
}
