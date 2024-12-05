package Observer;

public class TechStores implements Observer {
    private String name;

    public TechStores(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("TechStore " + name + " received notification: " + message);
    }


}
