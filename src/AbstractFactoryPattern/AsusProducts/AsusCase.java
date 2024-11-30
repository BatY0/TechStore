package AbstractFactoryPattern.AsusProducts;

import AbstractFactoryPattern.Components.Case;

public class AsusCase extends Case {

    public AsusCase(String model, double price, CaseType caseType, int fanCount) {
        super("Asus " + model, price, caseType, fanCount);
    }

    @Override
    public String getModel() {
        return getName();
    }
}
