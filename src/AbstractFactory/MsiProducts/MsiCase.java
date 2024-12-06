package AbstractFactory.MsiProducts;

import AbstractFactory.Components.Case;
import AbstractFactory.Components.FormFactor;

public class MsiCase extends Case {
    public MsiCase(String model, double price, FormFactor caseType, int fanCount) {
        super("MSI "+model, price, caseType, fanCount);
    }

    @Override
    public String getBrand() {
        return "MSI";
    }
}
