package Strategy;

/**
 * Concrete strategy class for express shipping.
 * Implements the CargoStrategy interface to provide
 * an express shipping cost calculation based on weight and distance.
 */
public interface CargoStrategy {
        double calculateShipping(Order order);
}
