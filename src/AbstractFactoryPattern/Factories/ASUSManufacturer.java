package AbstractFactoryPattern.Factories;

import AbstractFactoryPattern.AsusProducts.ASUSGpu;
import AbstractFactoryPattern.Components.Cpu;
import AbstractFactoryPattern.Components.Gpu;

public class ASUSManufacturer implements Company {
    @Override
    public Gpu createGpu(String model) {
        switch (model) {
            case "RTX3060": return new ASUSGpu("RTX3060",279.97,12,1320);
            case "RTX4060": return new ASUSGpu("RTX4060",298.67,8,1830);
            default: throw new IllegalArgumentException("ASUS does not produce this GPU model: " + model);
        }
    }

    @Override
    public Cpu createCpu(String model) {
        System.out.println("ASUS does not manufacture CPUs.");
        return null;
    }


}
