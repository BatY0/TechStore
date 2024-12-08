package Strategy;

/**
 * Concrete strategy class for express shipping.
 * Implements the CargoStrategy interface to provide
 * an express shipping cost calculation based on weight and distance.
 */
public class ExpressShipping implements CargoStrategy {
    @Override
    public double calculateShipping(Order order) {
        double baseRate = 10.0;
        double weightFactor = 0.15;
        double distanceFactor = 0.1;

        return baseRate + (order.getWeight() * weightFactor) + (order.getDistance() * distanceFactor);
    }
}

