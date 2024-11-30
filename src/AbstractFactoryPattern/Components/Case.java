package AbstractFactoryPattern.Components;

import AbstractFactoryPattern.Product;

public abstract class Case extends Product {



    private FormFactor formFactor;
    private int fanCount;

    // Constructor to initialize the common properties of a case
    public Case(String model, double price, FormFactor caseType, int fanCount) {
        super(model, price);
        this.formFactor = caseType;
        this.fanCount = fanCount;
    }

    // Getter for case type
    public FormFactor getFormFactor() {
        return formFactor;
    }

    // Getter for fan count
    public int getFanCount() {
        return fanCount;
    }

    // Abstract method to enforce model retrieval in subclasses
    public abstract String getModel();

    // Default description for a case
    @Override
    public String getDescription() {
        return String.format("%s (Case Type: %s, Fan Count: %d, Price: $%.2f)",
                getName(), formFactor, fanCount, getPrice());
    }
}
