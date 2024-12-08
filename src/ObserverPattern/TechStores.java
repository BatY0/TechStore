package ObserverPattern;

/**
 * TechStores class that implements Observer interface
 */

public class TechStores implements Observer {
    private String name;

    public TechStores(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("TechStore " + name + " received notification: " + message);
    }
}