package AbstractFactory.Factories;

import AbstractFactory.AsusProducts.*;
import AbstractFactory.Components.*;

public class ASUSManufacturer implements Company {
    @Override
    public Gpu createGpu(String model, int memorySize, double clockSpeed, double price) {
        return new AsusGpu(model, price, memorySize, clockSpeed);
    }

    @Override
    public Cpu createCpu(String model, SocketType socket, int cores, int threads, double clockSpeed, double price) {
        throw new UnsupportedOperationException("ASUS does not manufacture CPUs.");
    }

    @Override
    public Memory createMemory(String model, int capacity, int speed, MemoryType type, double price) {
        throw new UnsupportedOperationException("ASUS does not manufacture memory.");
    }

    @Override
    public Case createCase(String model, FormFactor formFactor, int fanCount, double price) {
        return new AsusCase(model, price, formFactor , fanCount);
    }

    @Override
    public CpuCooler createCpuCooler(String name, double price, CoolingType coolingType, int fanSpeed, double noiseLevel, String size) {
       return new AsusCpuCooler(name, price, coolingType, fanSpeed, noiseLevel, size);
    }

    @Override
    public Motherboard createMotherboard(String name, double price, SocketType socket, MemoryType memoryType, int memorySlots, FormFactor formFactor) {
        return new AsusMotherboard(name, price, socket, memoryType, memorySlots,formFactor);
    }

    @Override
    public PowerSupply createPowerSupply(String model, double price, int wattage, boolean modular) {
        return new AsusPowerSupply(model, price, wattage, modular);
    }

    @Override
    public Storage createStorage(String model, int capacity, StorageType type, double price) {
        throw new UnsupportedOperationException("ASUS does not manufacture storage.");
    }

}
