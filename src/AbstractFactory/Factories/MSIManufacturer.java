package AbstractFactory.Factories;

import AbstractFactory.Components.*;
import AbstractFactory.MsiProducts.*;

public class MSIManufacturer implements Company {


    @Override
    public Gpu createGpu(String model, int memorySize, double clockSpeed, double price) {
        return new MSIGpu(model,price,memorySize,clockSpeed);
    }

    @Override
    public Cpu createCpu(String model, SocketType socket, int cores, int threads, double clockSpeed, double price) {
        throw new UnsupportedOperationException("MSI does not manufacture CPU");
    }

    @Override
    public Memory createMemory(String model, int capacity, int speed, MemoryType type, double price) {
        throw new UnsupportedOperationException("MSI does not manufacture Memory");
    }

    @Override
    public Case createCase(String model, FormFactor formFactor, int fanCount, double price) {
        return new MsiCase(model, price, formFactor, fanCount);
    }

    @Override
    public CpuCooler createCpuCooler(String name, double price, CoolingType coolingType, int fanSpeed, double noiseLevel, String size) {
        return new MsiCpuCooler(name, price, coolingType, fanSpeed, noiseLevel, size);
    }

    @Override
    public Motherboard createMotherboard(String name, double price, SocketType socket, MemoryType memoryType, int memorySlots, FormFactor formFactor) {
        return new MsiMotherboard(name, price, socket, memoryType, memorySlots, formFactor);
    }

    @Override
    public PowerSupply createPowerSupply(String model, double price, int wattage, boolean modular) {
        return new MsiPowerSupply(model, price, wattage, modular);
    }

    @Override
    public Storage createStorage(String model, int capacity, StorageType type, double price) {
        return new MsiStorage(model, capacity, type, price);
    }


}
