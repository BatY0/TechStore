package AbstractFactory.CorsairProducts;

import AbstractFactory.Components.PowerSupply;

public class CorsairPowerSupply extends PowerSupply {
    public CorsairPowerSupply(String name, double price, int wattage, boolean modular) {
        super("Corsair "+name, price, wattage, modular);
    }

    @Override
    public String getBrand() {
        return "Corsair";
    }
}
