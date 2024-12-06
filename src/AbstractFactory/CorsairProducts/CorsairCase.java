package AbstractFactory.CorsairProducts;

import AbstractFactory.Components.Case;
import AbstractFactory.Components.FormFactor;

public class CorsairCase extends Case {
    public CorsairCase(String name, FormFactor formFactor, int numFans, double price) {
        super("Corsair "+name, price, formFactor, numFans);
    }

    @Override
    public String getBrand() {
        return "Corsair";
    }
}
