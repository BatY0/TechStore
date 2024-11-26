package CompositePattern;

import java.util.HashMap;
import java.util.Map;

public class HardwareStock implements Hardware {
    private Map<Hardware, Integer> hardwares = new HashMap<>();

    @Override
    public String getDescription() {
        StringBuilder description = new StringBuilder("Hardware Stock:\n");
        for (Map.Entry<Hardware, Integer> entry : hardwares.entrySet()) {
            Hardware hardware = entry.getKey();
            int quantity = entry.getValue();
            description.append(hardware.getDescription())
                    .append(" (Quantity: ")
                    .append(quantity)
                    .append(")\n");
        }
        return description.toString();
    }

    @Override
    public double getPrice() {
        double totalPrice = 0;
        for (Map.Entry<Hardware, Integer> entry : hardwares.entrySet()) {
            Hardware hardware = entry.getKey();
            int quantity = entry.getValue();
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
}
