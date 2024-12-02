package StrategyPattern;

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

    // ShippingStrategy'i kullanarak hesaplama yapılacak
    public double calculateShipping(CargoStrategy strategy) {
        return strategy.calculateShipping(this);
    }
}

