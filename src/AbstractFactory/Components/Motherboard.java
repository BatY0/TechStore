package AbstractFactory.Components;

import AbstractFactory.Product;


public abstract class Motherboard extends Product {
    private SocketType socket;
    private MemoryType memoryType;
    private int memorySlots;
    private FormFactor formFactor;

    public Motherboard(String name, double price, SocketType socket, MemoryType memoryType, int memorySlots, FormFactor formFactor) {
        super(name, price);
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


    @Override
    public String getDescription() {
        return String.format("%s (Socket: %s, Memory Type: %s, Memory Slots: %d, Form Factor: %s, Price: $%.2f)",
                getName(), socket, memoryType, memorySlots, formFactor, getPrice());
    }




}
