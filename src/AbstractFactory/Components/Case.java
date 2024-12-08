package AbstractFactory.Components;

import AbstractFactory.Product;


public abstract class Case extends Product {
    private FormFactor formFactor;
    private int fanCount;

    public Case(String name, double price, FormFactor caseType, int fanCount) {
        super(name, price);
        this.formFactor = caseType;
        this.fanCount = fanCount;
    }

    public FormFactor getFormFactor() {
        return formFactor;
    }

    public int getFanCount() {
        return fanCount;
    }

    @Override
    public String getDescription() {
        return String.format("%s (Case Type: %s, Fan Count: %d, Price: $%.2f)",
                getName(), formFactor, fanCount, getPrice());
    }
}
