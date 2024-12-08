package CompositeAndIterator;

import java.util.*;

public class HardwareStock implements Hardware {
    private final String name;
    private Map<Hardware, Integer> hardwares = new HashMap<>();

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

        } else {
            hardwares.put(hardware, currentCount - count);

        }
    }

    @Override
    public Hardware getChild(int index) {
        throw new UnsupportedOperationException("Not applicable for this implementation.");
    }

    @Override
    public String getBrand() {
        throw new UnsupportedOperationException("Not applicable for this implementation.");
    }


    @Override
    public Iterator<Hardware> createIterator() {
        return new CompositeIterator(hardwares.keySet().iterator());
    }

    public int getQuantity(Hardware hardware) {
        return hardwares.getOrDefault(hardware, 0);
    }

    public void setQuantity(Hardware hardware, int quantity) {
        hardwares.put(hardware, quantity);

    }


    //Finds a hardware component by its description.
     public Hardware findHardwareByDescription(String description) {
        Iterator<Hardware> iterator = createIterator();
        while (iterator.hasNext()) {
            Hardware hardware = iterator.next();
            if (hardware.getDescription().equals(description)) {
                return hardware;
            }
        }
        return null;
    }
    //Finds hardware components that match the given description.
    public List<Hardware> findHardwaresByDescription(String description) {
        List<Hardware> matchingHardware = new ArrayList<>();

        if (description == null || description.isBlank()) {
            return matchingHardware;
        }

        String lowerCaseDescription = description.toLowerCase();
        Iterator<Hardware> iterator = createIterator();
        while (iterator.hasNext()) {
            Hardware hardware = iterator.next();
            if (hardware.getDescription().toLowerCase().contains(lowerCaseDescription)) {
                matchingHardware.add(hardware);
            }
        }
        return matchingHardware;
    }


    @Override
    public void setPrice(double newUnitPrice) {
        throw new UnsupportedOperationException("Not applicable for this implementation.");

    }


}