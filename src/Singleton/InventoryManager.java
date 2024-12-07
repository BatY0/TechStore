package Singleton;

import AbstractFactory.Factories.*;
import CompositeAndIterator.Hardware;
import CompositeAndIterator.HardwareStock;
import ObserverPattern.Subject;
import ObserverPattern.TechStores;
import ObserverPattern.Observer;

import java.util.*;

public class InventoryManager implements Subject {
    private static InventoryManager instance;
    private final Map<String, HardwareStock> hardwareStocks;
    private final Map<String, Company> factories;
    private final List<Observer> observers = new ArrayList<>();

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
        addFactory("MSI", new MSIManufacturer());
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
            notifyObservers("Added " + count + " " + hardware.getDescription() + " to " + stockType + " stock");
        } else {
            System.out.println("Invalid stock type: " + stockType);
        }
    }

    public void setQuantity(String stockType, Hardware hardware, int count) {
        HardwareStock stock = hardwareStocks.get(stockType);
        if (stock != null) {
            stock.setQuantity(hardware, count);
            notifyObservers("Quantity of " + hardware.getDescription() + " in " + stockType + " stock has been updated to " + count);
        } else {
            System.out.println("Invalid stock type: " + stockType);
        }
    }

    public void setPrice(String stockType, Hardware hardware, double price) {
        HardwareStock stock = hardwareStocks.get(stockType);
        Iterator<Hardware> iterator = stock.createIterator();
        while (iterator.hasNext()) {
            Hardware h = iterator.next();
            if (h.equals(hardware)) {
                h.setPrice(price);
                notifyObservers("Price of " + hardware.getDescription() + " in " + stockType + " stock has been updated to " + price);
                break;
            }
        }

    }

    public void removeHardware(String stockType, Hardware hardware, int count) {
        HardwareStock stock = hardwareStocks.get(stockType);
        if (stock != null) {
            //delete the hardware from the stock
            if (count == -1) {
                stock.remove(hardware, stock.getQuantity(hardware));
                notifyObservers("Removed all " + hardware.getDescription() + " from " + stockType + " stock");
                return;
            }
            stock.remove(hardware, count);
            notifyObservers("Removed " + count + " " + hardware.getDescription() + " from " + stockType + " stock");
        } else {
            System.out.println("Invalid stock type: " + stockType);
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer); // Add observer to the list
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer); // Remove observer from the list
    }

    @Override
    public void notifyObservers() {
        notifyObservers("");
    }

    public Observer getObserver(String observerName) {
        for (Observer observer : observers) {
            if (observer instanceof TechStores) {
                TechStores store = (TechStores) observer;
                if (store.getName().equals(observerName)) {
                    return store;
                }
            }
        }
        return null;
    }

    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message); // Notify each observer with the message
        }
    }

    public void editObserverName(String oldName, String newName) {
        for (Observer observer : observers) {
            if (observer instanceof TechStores) {
                TechStores store = (TechStores) observer;
                if (store.getName().equals(oldName)) {
                    store.setName(newName);
                    break;
                }
            }
        }
    }

    public List<Observer> getObservers() {
        return observers;
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