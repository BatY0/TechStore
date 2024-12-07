package CompositeAndIterator;

import java.util.Iterator;

public interface Hardware {
    String getDescription();
    double getPrice();
    void add(Hardware hardware,int count);     // For composite components
    void remove(Hardware hardware,int count);  // For composite components
    Hardware getChild(int index);
    Iterator<Hardware> createIterator();       // For composite components
    void setPrice(double newUnitPrice);
    String getBrand();
}
