package CompositePattern;

public interface Hardware {
    String getDescription();
    double getPrice();
    void add(Hardware hardware,int count);     // For composite components
    void remove(Hardware hardware,int count);  // For composite components
    Hardware getChild(int index);
}
