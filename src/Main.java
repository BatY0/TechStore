import AbstractFactory.Components.*;
import CompositeAndIterator.Hardware;
import Singleton.InventoryManager;
import UI.UIMain;

import javax.swing.*;
import java.util.HashMap;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Add dummy products to the inventory
        addDummyProducts();
        // Initialize the UI
        SwingUtilities.invokeLater(() -> new UIMain().setVisible(true));


    }

    public static void addDummyProducts(){
        //use the singleton instance to add products
        InventoryManager inventoryManager = InventoryManager.getInstance();
        HashMap<String,Hardware[]> products = new HashMap<>();
        Hardware[] gpus = new Hardware[6];
        Hardware[] motherboards = new Hardware[4];
        Hardware[] cases = new Hardware[6];
        Hardware[] cpus = new Hardware[4];
        Hardware[] cpusCoolers = new Hardware[5];
        Hardware[] storages = new Hardware[4];
        Hardware[] memories = new Hardware[4];
        Hardware[] powerSupplies = new Hardware[6];
        gpus[0] = inventoryManager.getFactory("Asus").createGpu("RTX 3060", 8,1800,259.78);
        gpus[1] = inventoryManager.getFactory("Asus").createGpu("RTX 3070", 12,2200,359.78);
        gpus[2] = inventoryManager.getFactory("Asus").createGpu("RTX 3080", 10,2500,459.78);
        gpus[3] = inventoryManager.getFactory("MSI").createGpu("RTX 3060", 8,1800,289.99);
        gpus[4] = inventoryManager.getFactory("MSI").createGpu("RTX 3070", 12,2200,389.99);
        gpus[5] = inventoryManager.getFactory("MSI").createGpu("RTX 3080", 10,2500,489.99);
        motherboards[0] = inventoryManager.getFactory("Asus").createMotherboard("TUF Gaming", 169.99, SocketType.LGA1700, MemoryType.DDR5, 4, FormFactor.ATX);
        motherboards[1] = inventoryManager.getFactory("MSI").createMotherboard("MPG Z690", 199.99, SocketType.LGA1700, MemoryType.DDR5, 4, FormFactor.ATX);
        motherboards[2] = inventoryManager.getFactory("Asus").createMotherboard("ROG Strix", 299.99, SocketType.LGA1700, MemoryType.DDR5, 4, FormFactor.ATX);
        motherboards[3] = inventoryManager.getFactory("MSI").createMotherboard("MAG B660", 129.99, SocketType.LGA1700, MemoryType.DDR5, 4, FormFactor.ATX);
        cases[0] = inventoryManager.getFactory("Corsair").createCase("4000D",  FormFactor.ATX, 4, 79.99);
        cases[1] = inventoryManager.getFactory("Corsair").createCase("5000D",  FormFactor.ATX, 5, 99.99);
        cases[2] = inventoryManager.getFactory("MSI").createCase("MAG Forge 100R",  FormFactor.ATX, 4, 59.99);
        cases[3] = inventoryManager.getFactory("MSI").createCase("MAG Forge 100M",  FormFactor.MICRO_ATX, 4, 49.99);
        cases[4] = inventoryManager.getFactory("Asus").createCase("TUF Gaming GT301",  FormFactor.MICRO_ATX, 4, 69.99);
        cases[5] = inventoryManager.getFactory("Asus").createCase("TUF Gaming GT501",  FormFactor.ATX, 4, 89.99);
        cpus[0] = inventoryManager.getFactory("Intel").createCpu("i5-12600K", SocketType.LGA1700, 6, 12, 3.7, 269.99);
        cpus[1] = inventoryManager.getFactory("Intel").createCpu("i7-12700K", SocketType.LGA1700, 8, 16, 3.6, 399.99);
        cpus[2] = inventoryManager.getFactory("Intel").createCpu("i9-12900K", SocketType.LGA1700, 16, 24, 3.2, 599.99);
        cpus[3] = inventoryManager.getFactory("Intel").createCpu("i3-12100", SocketType.LGA1700, 4, 8, 3.4, 129.99);
        cpusCoolers[0] = inventoryManager.getFactory("Corsair").createCpuCooler("H100i", 139.99, CoolingType.Liquid, 1850, 25,"240mm");
        cpusCoolers[1] = inventoryManager.getFactory("Corsair").createCpuCooler("H150i", 169.99, CoolingType.Liquid, 2000, 30,"360mm");
        cpusCoolers[2] = inventoryManager.getFactory("MSI").createCpuCooler("MAG CoreLiquid 240R", 99.99, CoolingType.Liquid, 1800, 25,"240mm");
        cpusCoolers[3] = inventoryManager.getFactory("MSI").createCpuCooler("Core Frozr L", 79.99, CoolingType.Air, 2000, 30,"120mm");
        cpusCoolers[4] = inventoryManager.getFactory("Asus").createCpuCooler("ROG Strix LC 240", 139.99, CoolingType.Liquid, 1850, 25,"240mm");
        storages[0] = inventoryManager.getFactory("Corsair").createStorage("MP600", 2048, StorageType.SSD, 179.99);
        storages[1] = inventoryManager.getFactory("Corsair").createStorage("MP510", 1024, StorageType.SSD, 99.99);
        storages[2] = inventoryManager.getFactory("MSI").createStorage("Spatium M480", 2048, StorageType.SSD, 149.99);
        storages[3] = inventoryManager.getFactory("MSI").createStorage("Spatium M470", 1024, StorageType.SSD, 79.99);
        memories[0] = inventoryManager.getFactory("Corsair").createMemory("Vengeance DDR4 RGB Pro", 32, 3200,MemoryType.DDR4, 69.99);
        memories[1] = inventoryManager.getFactory("Corsair").createMemory("Vengeance DDR4 LPX", 16, 3200,MemoryType.DDR4, 49.99);
        memories[2] =inventoryManager.getFactory("Corsair").createMemory("Vengeance DDR5 RGB Pro ", 32, 6000,MemoryType.DDR5, 99.99);
        memories[3] = inventoryManager.getFactory("Corsair").createMemory("Vengeance DDR5 LPX", 16, 6000,MemoryType.DDR5, 89.99);
        powerSupplies[0] = inventoryManager.getFactory("Corsair").createPowerSupply("RM850", 139.99,850, true);
        powerSupplies[1] = inventoryManager.getFactory("Corsair").createPowerSupply("RM750", 119.99,750, true);
        powerSupplies[2] = inventoryManager.getFactory("MSI").createPowerSupply("MPG A850GF", 149.99,850, true);
        powerSupplies[3] = inventoryManager.getFactory("MSI").createPowerSupply("MPG A750GF", 129.99,750, true);
        powerSupplies[4] = inventoryManager.getFactory("Asus").createPowerSupply("ROG Strix 850G", 159.99,850, true);
        powerSupplies[5] = inventoryManager.getFactory("Asus").createPowerSupply("ROG Strix 750G", 139.99,750, true);

        products.put("GPU",gpus);
        products.put("Motherboard",motherboards);
        products.put("Case",cases);
        products.put("CPU",cpus);
        products.put("CpuCooler",cpusCoolers);
        products.put("Storage",storages);
        products.put("Memory",memories);
        products.put("PowerSupply",powerSupplies);

        Random random = new Random();


        for (String key: products.keySet()){
            for (Hardware product: products.get(key)){
                inventoryManager.addHardware(key,product,random.nextInt(5,10));
            }

        }




    }
}