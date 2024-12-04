package AbstractFactoryPattern.Components;

import AbstractFactoryPattern.Product;
import CompositePattern.Hardware;

public abstract class PowerSupply extends Product {
    private int wattage;
    private boolean modular;

    public PowerSupply(String model, double price, int wattage, boolean modular) {
        super(model, price);
        this.wattage = wattage;
        this.modular = modular;
    }

    public int getWattage() {
        return wattage;
    }

    public boolean isModular() {
        return modular;
    }



    @Override
    public String getDescription() {
        return String.format("%s (Wattage: %dW, Modular: %b, Price: $%.2f)",
                getName(), wattage, modular, getPrice());
    }
}
