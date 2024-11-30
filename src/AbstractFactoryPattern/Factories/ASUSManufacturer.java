package AbstractFactoryPattern.Factories;

import AbstractFactoryPattern.AsusProducts.ASUSGpu;
import AbstractFactoryPattern.AsusProducts.AsusCase;
import AbstractFactoryPattern.Components.*;

public class ASUSManufacturer implements Company {
    @Override
    public Gpu createGpu(String model, int memorySize, double clockSpeed, double price) {
        return new ASUSGpu(model, price, memorySize, clockSpeed);
    }

    @Override
    public Cpu createCpu(String model, String socket, int cores, int threads, double clockSpeed, double price) {
        throw new UnsupportedOperationException("ASUS does not manufacture CPUs.");
    }

    @Override
    public Memory createMemory(String model, int capacity, int speed, MemoryType type, double price) {
        throw new UnsupportedOperationException("ASUS does not manufacture memory.");
    }

    @Override
    public Case createCase(String model, Case.CaseType caseType, int fanCount, double price) {
        return new AsusCase(model, price, caseType, fanCount);
    }

    @Override
    public CpuCooler createCpuCooler(String model, CpuCooler.CoolingType coolingType, int fanSpeed, double noiseLevel, double coolingCapacity, String size, double price) {
       throw new UnsupportedOperationException("ASUS does not manufacture cpu cooler.");
    }


}
