package CompositeAndIterator;

import Observer.Observer;
import Observer.Subject;

import java.util.*;

public class HardwareStock implements Hardware, Subject {
    private final String name;
    private Map<Hardware, Integer> hardwares = new HashMap<>();
    private List<Observer> observers = new ArrayList<>();

    public HardwareStock(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return name;
    }

    @Override
    public double getPrice() {
        double totalPrice = 0;
        Iterator<Hardware> iterator = createIterator();
        while (iterator.hasNext()) {
            Hardware hardware = iterator.next();
            int quantity = hardwares.get(hardware);
            totalPrice += hardware.getPrice() * quantity;
        }
        totalPrice = Math.round(totalPrice * 100) / 100.0;
        return totalPrice;
    }

    @Override
    public void add(Hardware hardware, int count) {
        if (count <= 0) {
            System.out.println("Count must be greater than 0.");
            return;
        }
        hardwares.put(hardware, hardwares.getOrDefault(hardware, 0) + count);
        notifyObservers("Added " + count + " of " + hardware.getDescription());
    }

    @Override
    public void remove(Hardware hardware, int count) {
        if (count <= 0) {
            System.out.println("Count must be greater than 0.");
            return;
        }

        if (!hardwares.containsKey(hardware)) {
            System.out.println("Hardware not found in stock.");
            return;
        }

        int currentCount = hardwares.get(hardware);
        if (currentCount <= count) {
            hardwares.remove(hardware);
            notifyObservers("Removed all of " + hardware.getDescription());
        } else {
            hardwares.put(hardware, currentCount - count);
            notifyObservers("Removed " + count + " of " + hardware.getDescription());
        }
    }

    @Override
    public Hardware getChild(int index) {
        throw new UnsupportedOperationException("Not applicable for this implementation.");
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        notifyObservers("");
    }

    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    @Override
    public Iterator<Hardware> createIterator() {
        return new CompositeIterator(hardwares.keySet().iterator());
    }

    public int getQuantity(Hardware hardware) {
        return hardwares.getOrDefault(hardware, 0);
    }
}