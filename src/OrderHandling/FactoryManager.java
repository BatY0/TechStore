package OrderHandling;

import AbstractFactoryPattern.Factories.Company;

import java.util.HashMap;
import java.util.Map;

public class FactoryManager {
    private static final Map<String, Company> factoryRegistry = new HashMap<>();

    public static void registerFactory(String manufacturer, Company factory) {
        factoryRegistry.put(manufacturer.toLowerCase(), factory);
    }

    public static Company getFactory(String manufacturer) {
        return factoryRegistry.get(manufacturer.toLowerCase());
    }

    public static String[] getAvailableManufacturers() {
        return factoryRegistry.keySet().toArray(new String[0]);
    }
}
