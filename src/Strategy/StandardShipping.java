package Strategy;

/**
 * The Order class represents an order with a specific weight and distance.
 * It provides methods to get the weight and distance of the order, and to calculate
 * the shipping cost using a given CargoStrategy.
 */
public class StandardShipping implements CargoStrategy {
    @Override
    public double calculateShipping(Order order) {
        double baseRate = 5.0;
        double weightFactor = 0.1;
        double distanceFactor = 0.05;

        return baseRate + (order.getWeight() * weightFactor) + (order.getDistance() * distanceFactor);
    }
}

