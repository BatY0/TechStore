package CompositeAndIterator;

import java.util.Iterator;

/**
 * The Hardware interface represents a component in a composite structure.
 * It defines methods for managing and accessing hardware components.
 */
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
