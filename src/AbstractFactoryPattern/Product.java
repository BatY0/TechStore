package AbstractFactoryPattern;

import CompositePattern.Hardware;

public abstract class Product implements Hardware {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getDescription() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void add(Hardware hardware,int count) {
        throw new UnsupportedOperationException("Cannot add to a leaf product.");//This for the composite products
    }

    @Override
    public void remove(Hardware hardware, int count) {
        throw new UnsupportedOperationException("Cannot remove from a leaf product.");//This for the composite products
    }

    @Override
    public Hardware getChild(int index) {
        throw new UnsupportedOperationException("Leaf product does not have children.");//This for the composite products
    }
}


