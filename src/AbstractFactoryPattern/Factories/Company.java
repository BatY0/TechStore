package AbstractFactoryPattern.Factories;

import AbstractFactoryPattern.Components.*;


public interface Company {

    // Method to create a GPU
    Gpu createGpu(String model, int memorySize, double clockSpeed, double price);

    // Method to create a CPU
    Cpu createCpu(String model, String socket, int cores, int threads, double clockSpeed, double price);

    // Method to create a Memory
    Memory createMemory(String model, int capacity, int speed, MemoryType type, double price);

    // Method to create a Case
    Case createCase(String model, Case.CaseType caseType, int fanCount, double price);

    // Method to create a CPU Cooler
    CpuCooler createCpuCooler(String model, CpuCooler.CoolingType coolingType, int fanSpeed, double noiseLevel, double coolingCapacity, String size, double price);
}
