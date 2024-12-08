package Strategy;


/**
 * The Order class represents an order with a specific weight and distance.
 * It provides methods to get the weight and distance of the order, and to calculate
 * the shipping cost using a given CargoStrategy.
 */
public class Order {
    private double weight;
    private double distance;

    public Order(double weight, double distance) {
        this.weight = weight;
        this.distance = distance;
    }

    public double getWeight() {
        return weight;
    }

    public double getDistance() {
        return distance;
    }

    public double calculateShipping(CargoStrategy strategy) {
        return strategy.calculateShipping(this);
    }
}


