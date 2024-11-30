package AbstractFactoryPattern.Factories;

import AbstractFactoryPattern.Components.*;
import AbstractFactoryPattern.MsiProducts.MSIGpu;
import AbstractFactoryPattern.MsiProducts.MsiCpuCooler;

public class MSIManufacturer implements Company {


    @Override
    public Gpu createGpu(String model, int memorySize, double clockSpeed, double price) {
        return new MSIGpu(model,price,memorySize,clockSpeed);
    }

    @Override
    public Cpu createCpu(String model, String socket, int cores, int threads, double clockSpeed, double price) {
        throw new UnsupportedOperationException("MSI does not manufacture CPU");
    }

    @Override
    public Memory createMemory(String model, int capacity, int speed, MemoryType type, double price) {
        throw new UnsupportedOperationException("MSI does not manufacture Memory");
    }

    @Override
    public Case createCase(String model, FormFactor formFactor, int fanCount, double price) {
        throw new UnsupportedOperationException("MSI does not manufacture Case");
    }

    @Override
    public CpuCooler createCpuCooler(String model, CpuCooler.CoolingType coolingType, int fanSpeed, double noiseLevel, double coolingCapacity, String size, double price) {
        return new MsiCpuCooler(model, price, coolingType, fanSpeed, noiseLevel, coolingCapacity, size);
    }
}
