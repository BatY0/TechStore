package AbstractFactory.Factories;

import AbstractFactory.Components.*;
import AbstractFactory.IntelProducts.IntelCpu;
import AbstractFactory.IntelProducts.IntelGpu;

public class IntelManufacturer implements Company{

    @Override
    public Gpu createGpu(String model, int memorySize, double clockSpeed, double price) {
        return new IntelGpu(model,memorySize,clockSpeed,price);
    }

    @Override
    public Cpu createCpu(String model, SocketType socket, int cores, int threads, double clockSpeed, double price) {
        return new IntelCpu(model, socket, cores, threads, clockSpeed, price);
    }

    @Override
    public Memory createMemory(String model, int capacity, int speed, MemoryType type, double price) {
        throw new UnsupportedOperationException("Intel does not manufacture memory.");
    }

    @Override
    public Case createCase(String model, FormFactor formFactor, int fanCount, double price) {
        throw new UnsupportedOperationException("Intel does not manufacture cases.");
    }

    @Override
    public CpuCooler createCpuCooler(String name, double price, CoolingType coolingType, int fanSpeed, double noiseLevel, String size) {
        throw new UnsupportedOperationException("Intel does not manufacture CPU coolers.");
    }

    @Override
    public Motherboard createMotherboard(String name, double price, SocketType socket, MemoryType memoryType, int memorySlots, FormFactor formFactor) {
        throw new UnsupportedOperationException("Intel does not manufacture motherboards.");
    }

    @Override
    public PowerSupply createPowerSupply(String model, double price, int wattage, boolean modular) {
        throw new UnsupportedOperationException("Intel does not manufacture power supplies.");
    }

    @Override
    public Storage createStorage(String model, int capacity, StorageType type, double price) {
        throw new UnsupportedOperationException("Intel does not manufacture storage.");
    }
}
