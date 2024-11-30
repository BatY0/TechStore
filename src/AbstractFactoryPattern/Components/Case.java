package AbstractFactoryPattern.Components;

import AbstractFactoryPattern.Product;
import CompositePattern.Hardware;

public abstract class Case extends Product {

    public enum CaseType {
        ATX_FULL_TOWER,
        ATX_MID_TOWER,
        MICRO_ATX,
        MINI_ITX
    }

    private CaseType caseType;
    private int fanCount;

    // Constructor to initialize the common properties of a case
    public Case(String model, double price, CaseType caseType, int fanCount) {
        super(model, price);
        this.caseType = caseType;
        this.fanCount = fanCount;
    }

    // Getter for case type
    public CaseType getCaseType() {
        return caseType;
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
                getName(), caseType, fanCount, getPrice());
    }
}
