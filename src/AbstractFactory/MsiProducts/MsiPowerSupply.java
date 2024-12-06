package AbstractFactory.MsiProducts;

import AbstractFactory.Components.PowerSupply;

public class MsiPowerSupply extends PowerSupply {
    public MsiPowerSupply(String model, double price, int wattage, boolean modular) {
        super("MSI " + model, price, wattage, modular);
    }

    @Override
    public String getBrand() {
        return "MSI";
    }
}
