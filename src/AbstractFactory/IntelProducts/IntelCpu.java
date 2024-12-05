package AbstractFactory.IntelProducts;


import AbstractFactory.Components.Cpu;
import AbstractFactory.Components.SocketType;

public class IntelCpu extends Cpu {

    public IntelCpu(String model, SocketType socket, int coreCount, int threadCount, double clockSpeed, double price) {
        super("Intel " + model, price, socket, coreCount, threadCount, clockSpeed);
    }

    @Override
    public String getBrand() {
        return "Intel";
    }
}