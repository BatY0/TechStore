package OrderHandling;

import AbstractFactoryPattern.Components.*;
import AbstractFactoryPattern.Factories.ASUSManufacturer;
import AbstractFactoryPattern.Factories.Company;
import AbstractFactoryPattern.Product;

import java.util.ArrayList;
import java.util.List;

public abstract class OrderHandler {
    private Company company;

    OrderHandler(Company company) {
        this.company = company;
    }

    public void orderGpu(String model, int memorySize, double clockSpeed, double price) {
        Gpu gpu = company.createGpu(model, memorySize, clockSpeed, price);

    }

    public void orderCpu(String model, String socket, int cores, int threads, double clockSpeed, double price) {
        Cpu cpu = company.createCpu(model, socket, cores, threads, clockSpeed, price);
    }

    public void orderMemory(String model, int capacity, int speed, MemoryType type, double price) {
        Memory memory = company.createMemory(model, capacity, speed, type, price);
    }

    public void orderCase(String model, Case.CaseType caseType, int fanCount, double price) {
        Case pcCase = company.createCase(model, caseType, fanCount, price);
    }

    public void orderCpuCooler(String model, CpuCooler.CoolingType coolingType, int fanSpeed, double noiseLevel, double coolingCapacity, String size, double price) {
        CpuCooler cpuCooler = company.createCpuCooler(model, coolingType, fanSpeed, noiseLevel, coolingCapacity, size, price);

    }



}