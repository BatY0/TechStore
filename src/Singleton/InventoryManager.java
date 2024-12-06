package Singleton;

import AbstractFactory.Factories.ASUSManufacturer;
import AbstractFactory.Factories.Company;
import AbstractFactory.Factories.CorsairManufacturer;
import AbstractFactory.Factories.IntelManufacturer;
import CompositeAndIterator.Hardware;
import CompositeAndIterator.HardwareStock;
import Observer.Observer;

import java.util.*;

public class InventoryManager {
    private static InventoryManager instance;
    private final Map<String, HardwareStock> hardwareStocks;
    private final Map<String, Company> factories;

    private InventoryManager() {
        hardwareStocks = new HashMap<>();
        factories = new HashMap<>();
        initializeHardwareStocks();
        initializeFactories();
    }

    public static synchronized InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    private void initializeFactories() {
        addFactory("ASUS", new ASUSManufacturer());
        addFactory("MSI", new ASUSManufacturer());
        addFactory("Intel", new IntelManufacturer());
        addFactory("Corsair", new CorsairManufacturer());
    }

    private void initializeHardwareStocks() {
        hardwareStocks.put("GPU", new HardwareStock("GPU"));
        hardwareStocks.put("Storage", new HardwareStock("Storage"));
        hardwareStocks.put("CPU", new HardwareStock("CPU"));
        hardwareStocks.put("CpuCooler", new HardwareStock("CpuCooler"));
        hardwareStocks.put("PowerSupply", new HardwareStock("PowerSupply"));
        hardwareStocks.put("Motherboard", new HardwareStock("Motherboard"));
        hardwareStocks.put("Memory", new HardwareStock("Memory"));
        hardwareStocks.put("Case", new HardwareStock("Case"));
    }

    public void addFactory(String name, Company factory) {
        factories.put(name, factory);
    }

    public Company getFactory(String name) {
        return factories.get(name);
    }

    public String[] getFactoryNames() {
        return factories.keySet().toArray(new String[0]);
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
        hardwareStocks.values().forEach(stock -> stock.registerObserver(observer));
    }

    public void removeObserver(Observer observer) {
        hardwareStocks.values().forEach(stock -> stock.removeObserver(observer));
    }

    public Map<String, HardwareStock> getHardwareStocks() {
        return Collections.unmodifiableMap(hardwareStocks);
    }

    public HardwareStock getHardwareStock(String stockType) {
        return hardwareStocks.getOrDefault(stockType, null);
    }

    public List<Hardware> getAllHardware() {
        List<Hardware> allHardware = new ArrayList<>();
        for (HardwareStock stock : hardwareStocks.values()) {
            Iterator<Hardware> iterator = stock.createIterator();
            while (iterator.hasNext()) {
                allHardware.add(iterator.next());
            }
        }
        return allHardware;
    }
}
