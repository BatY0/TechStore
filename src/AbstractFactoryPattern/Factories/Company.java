package AbstractFactoryPattern.Factories;

import AbstractFactoryPattern.Components.Cpu;
import AbstractFactoryPattern.Components.Gpu;

public interface Company {
    Gpu createGpu(String model);
    Cpu createCpu(String model);
}
