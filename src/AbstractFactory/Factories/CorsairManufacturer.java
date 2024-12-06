package AbstractFactory.Factories;

import AbstractFactory.Components.*;
import AbstractFactory.CorsairProducts.*;

public class CorsairManufacturer implements Company {
    @Override
    public Gpu createGpu(String model, int memorySize, double clockSpeed, double price) {
        throw new UnsupportedOperationException("Corsair does not manufacture GPUs.");
    }

    @Override
    public Cpu createCpu(String model, SocketType socket, int cores, int threads, double clockSpeed, double price) {
        throw new UnsupportedOperationException("Corsair does not manufacture CPUs.");
    }

    @Override
    public Memory createMemory(String model, int capacity, int speed, MemoryType type, double price) {
        return new CorsairMemory(model, price, capacity, speed, type);
    }

    @Override
    public Case createCase(String model, FormFactor formFactor, int fanCount, double price) {
        return new CorsairCase(model, formFactor, fanCount, price);
    }

    @Override
    public CpuCooler createCpuCooler(String name, double price, CoolingType coolingType, int fanSpeed, double noiseLevel, String size) {
        return new CorsairCooler(name, price, coolingType, fanSpeed, noiseLevel, size);
    }

    @Override
    public Motherboard createMotherboard(String name, double price, SocketType socket, MemoryType memoryType, int memorySlots, FormFactor formFactor) {
        throw new UnsupportedOperationException("Corsair does not manufacture motherboards.");
    }

    @Override
    public PowerSupply createPowerSupply(String name, double price, int wattage, boolean modular) {
        return new CorsairPowerSupply(name, price, wattage, modular);
    }

    @Override
    public Storage createStorage(String model, int capacity, StorageType type, double price) {
        return new CorsairStorage(model, price, capacity,type);
    }
}
