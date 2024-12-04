package AbstractFactoryPattern.Components;


public class IntelCpu extends Cpu {

    public IntelCpu(String model, SocketType socket, int coreCount, int threadCount, double clockSpeed, double price) {
        super("Intel " + model, price, socket, coreCount, threadCount, clockSpeed);
    }

    @Override
    public String getBrand() {
        return "Intel";
    }
}