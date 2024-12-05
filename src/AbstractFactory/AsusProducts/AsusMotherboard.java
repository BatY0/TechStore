package AbstractFactory.AsusProducts;

import AbstractFactory.Components.FormFactor;
import AbstractFactory.Components.MemoryType;
import AbstractFactory.Components.Motherboard;
import AbstractFactory.Components.SocketType;

public class AsusMotherboard extends Motherboard {
    public AsusMotherboard(String name, double price, SocketType socket, MemoryType memoryType, int memorySlots, FormFactor formFactor) {
        super("Asus " +name, price, socket,memoryType, memorySlots, formFactor);
    }

    @Override
    public String getBrand() {
        return "ASUS";
    }
}
