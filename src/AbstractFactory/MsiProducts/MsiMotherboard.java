package AbstractFactory.MsiProducts;

import AbstractFactory.Components.FormFactor;
import AbstractFactory.Components.MemoryType;
import AbstractFactory.Components.Motherboard;
import AbstractFactory.Components.SocketType;

public class MsiMotherboard extends Motherboard {
    public MsiMotherboard(String name, double price, SocketType socket, MemoryType memoryType, int memorySlots, FormFactor formFactor) {
        super("MSI " + name, price, socket, memoryType, memorySlots, formFactor);
    }

    @Override
    public String getBrand() {
        return "MSI";
    }
}
