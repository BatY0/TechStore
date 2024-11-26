package AbstractFactoryPattern.Factories;

import AbstractFactoryPattern.Components.Cpu;
import AbstractFactoryPattern.Components.GpuPackage.Gpu;
import AbstractFactoryPattern.Components.GpuPackage.MSIGpu;

public class MSIManufacturer implements Company {
    @Override
    public Gpu createGpu(String model) {
        switch (model) {
            case "RTX3060": return new MSIGpu("RTX3060",264.99,8,1320);
            case "RTX4060": return new MSIGpu("RTX4060",289,12,1830);
            default: throw new IllegalArgumentException("MSI does not produce this GPU model: " + model);
        }
    }

    @Override
    public Cpu createCpu(String model) {
        System.out.println("MSI does not manufacture CPUs.");
        return null;
    }


}
