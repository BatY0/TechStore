package AbstractFactory.AsusProducts;

import AbstractFactory.Components.PowerSupply;

public class AsusPowerSupply extends PowerSupply {
    public AsusPowerSupply(String model, double price, int wattage, boolean modular) {
        super("Asus "+model, price, wattage, modular);
    }

    @Override
    public String getBrand() {
        return "Asus";
    }
}
