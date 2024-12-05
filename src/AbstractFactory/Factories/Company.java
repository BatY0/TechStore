package AbstractFactory.Factories;

import AbstractFactory.Components.*;

//Birden fazla factory var bunların kendine ait ürünleri var sadece onların ürünlerini üretebilirler bunu uida belirtip seçtircem
public interface Company {

    // Method to create a GPU
    Gpu createGpu(String model, int memorySize, double clockSpeed, double price);

    // Method to create a CPU
    Cpu createCpu(String model, String socket, int cores, int threads, double clockSpeed, double price);

    // Method to create a Memory
    Memory createMemory(String model, int capacity, int speed, MemoryType type, double price);

    // Method to create a Case
    Case createCase(String model, FormFactor formFactor, int fanCount, double price);

    // Method to create a CPU Cooler
    CpuCooler createCpuCooler(String name, double price, CoolingType coolingType, int fanSpeed, double noiseLevel, String size);

    // Method to create a Motherboard
    Motherboard createMotherboard(String name, double price, SocketType socket, MemoryType memoryType, int memorySlots, FormFactor formFactor);

    // Method to create a Power Supply
    PowerSupply createPowerSupply(String model, double price, int wattage, boolean modular);

    // Method to create a Storage
    Storage createStorage(String model, int capacity, int speed, StorageType type, double price);




}
