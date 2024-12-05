package AbstractFactory.AsusProducts;

import AbstractFactory.Components.Case;
import AbstractFactory.Components.FormFactor;

public class AsusCase extends Case {

    public AsusCase(String model, double price, FormFactor caseType, int fanCount) {
        super("Asus " + model, price, caseType, fanCount);
    }

    @Override
    public String getBrand() {
        return "Asus";
    }
}
