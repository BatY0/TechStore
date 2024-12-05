package AbstractFactory.Factories;

import AbstractFactory.Components.*;
import AbstractFactory.MsiProducts.MSIGpu;
import AbstractFactory.MsiProducts.MsiCpuCooler;

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
    public CpuCooler createCpuCooler(String name, double price, CoolingType coolingType, int fanSpeed, double noiseLevel, String size) {
        return null;
    }

    @Override
    public Motherboard createMotherboard(String name, double price, SocketType socket, MemoryType memoryType, int memorySlots, FormFactor formFactor) {
        return null;
    }

    @Override
    public PowerSupply createPowerSupply(String model, double price, int wattage, boolean modular) {
        return null;
    }

    @Override
    public Storage createStorage(String model, int capacity, int speed, StorageType type, double price) {
        return null;
    }


}
