package Singleton;

import AbstractFactoryPattern.Factories.Company;
import CompositePattern.Hardware;
import CompositePattern.HardwareStock;
import ObserverPattern.Observer;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private static InventoryManager instance;
    private Map<String, HardwareStock> hardwareStocks;
    private Map<String, Company> factories;

    private InventoryManager() {
        hardwareStocks = new HashMap<>();
        factories = new HashMap<>();
        initializeHardwareStocks();
    }

    public static synchronized InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    private void initializeHardwareStocks() {
        hardwareStocks.put("GPU", new HardwareStock("GPU"));
        hardwareStocks.put("Storage", new HardwareStock("Storage"));
        hardwareStocks.put("CPU", new HardwareStock("CPU"));
        hardwareStocks.put("CpuCooler",new HardwareStock("CpuCooler"));
        hardwareStocks.put("PowerSupply",new HardwareStock("PowerSupply"));
        hardwareStocks.put("Motherboard",new HardwareStock("Motherboard"));
        hardwareStocks.put("Memory",new HardwareStock("Memory"));
        hardwareStocks.put("Case",new HardwareStock("Case"));

    }

    public void addFactory(String name, Company factory) {
        factories.put(name, factory);
    }

    public Company getFactory(String name) {
        return factories.get(name);
    }

    public void addHardware(String stockType, Hardware hardware, int count) {
        HardwareStock stock = hardwareStocks.get(stockType);
        if (stock != null) {
            stock.add(hardware, count);
        } else {
            System.out.println("Invalid stock type: " + stockType);
        }
    }

    public void removeHardware(String stockType, Hardware hardware, int count) {
        HardwareStock stock = hardwareStocks.get(stockType);
        if (stock != null) {
            stock.remove(hardware, count);
        } else {
            System.out.println("Invalid stock type: " + stockType);
        }
    }

    public void registerObserver(Observer observer) {
        for (HardwareStock stock : hardwareStocks.values()) {
            stock.registerObserver(observer);
        }
    }

    public void removeObserver(Observer observer) {
        for (HardwareStock stock : hardwareStocks.values()) {
            stock.removeObserver(observer);
        }
    }

    public String getInventoryDescription(String stockType) {
        HardwareStock stock = hardwareStocks.get(stockType);
        if (stock != null) {
            return stock.getDescription();
        } else {
            return "Invalid stock type: " + stockType;
        }
    }

    public double getInventoryPrice(String stockType) {
        HardwareStock stock = hardwareStocks.get(stockType);
        if (stock != null) {
            return stock.getPrice();
        } else {
            return 0.0;
        }
    }
}