package Strategy;

public class StandardShipping implements CargoStrategy {
    @Override
    public double calculateShipping(Order order) {
        double baseRate = 5.0; // Temel ücret
        double weightFactor = 0.1; // Ağırlık başına ücret
        double distanceFactor = 0.05; // Mesafe başına ücret

        return baseRate + (order.getWeight() * weightFactor) + (order.getDistance() * distanceFactor);
    }
}

