package AbstractFactoryPattern.Components;

import AbstractFactoryPattern.Product;


public abstract class Motherboard extends Product {
    private SocketType socket;
    private MemoryType memoryType;
    private int memorySlots;
    private FormFactor formFactor;

    public Motherboard(String model, double price, SocketType socket, MemoryType memoryType, int memorySlots, FormFactor formFactor) {
        super(model, price);
        this.socket = socket;
        this.memoryType = memoryType;
        this.memorySlots = memorySlots;
        this.formFactor = formFactor;
    }

    public SocketType getSocket() {
        return socket;
    }

    public MemoryType getMemoryType() {
        return memoryType;
    }

    public int getMemorySlots() {
        return memorySlots;
    }

    public FormFactor getFormFactor() {
        return formFactor;
    }

    public abstract String getModel();

    @Override
    public String getDescription() {
        return String.format("%s (Socket: %s, Memory Type: %s, Memory Slots: %d, Form Factor: %s, Price: $%.2f)",
                getModel(), socket, memoryType, memorySlots, formFactor, getPrice());
    }




}
