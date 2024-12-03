
import AbstractFactoryPattern.Components.FormFactor;
import CompositePattern.Hardware;
import Singleton.InventoryManager;
import AbstractFactoryPattern.Factories.ASUSManufacturer;
import AbstractFactoryPattern.Factories.Company;
import ObserverPattern.TechStores;

public class Main {
    public static void main(String[] args) {
        // Get the singleton instance of InventoryManager
        InventoryManager inventoryManager = InventoryManager.getInstance();

        // Create and add factories
        Company asusFactory = new ASUSManufacturer();
        inventoryManager.addFactory("ASUS", asusFactory);

        // Create some products using the factory
        Hardware asusGpu = asusFactory.createGpu("RTX 3080", 10, 1.7, 699.99);
        Hardware asusCase = asusFactory.createCase("Rog Strix", FormFactor.ATX, 3, 199.99);

        // Create some tech stores
        TechStores store1 = new TechStores("Store 1");
        TechStores store2 = new TechStores("Store 2");

        // Register observers
        inventoryManager.registerObserver(store1);
        inventoryManager.registerObserver(store2);

        // Add hardware to the inventory
        inventoryManager.addHardware("GPU", asusGpu, 10);
        inventoryManager.addHardware("Case", asusCase, 5);

        // Print inventory description and price for each stock type
        System.out.println(inventoryManager.getInventoryDescription("GPU"));
        System.out.println("Total GPU Inventory Price: " + inventoryManager.getInventoryPrice("GPU"));

        System.out.println(inventoryManager.getInventoryDescription("Case"));
        System.out.println("Total CPU Inventory Price: " + inventoryManager.getInventoryPrice("Case"));
    }
}