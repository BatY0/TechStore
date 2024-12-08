package AbstractFactory;

import CompositeAndIterator.Hardware;

import java.util.Iterator;

/**
 * Product class is the abstract class that implements Hardware interface.
 * It creates base abstract methods for the products. From this class we can create more specific products.
 *
 */

public abstract class Product implements Hardware {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getName(){
        return name;
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
    public void setPrice(double price) {
        this.price = price;

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
    public abstract String getBrand();


    //Override equals and hashCode methods to compare the products by their names
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return getName().equals(product.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }


    @Override
    public Iterator<Hardware> createIterator() {
        throw new UnsupportedOperationException("Leaf product does not have children.");//This for the composite products
    }
}


