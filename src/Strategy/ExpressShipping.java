package Strategy;

public class ExpressShipping implements CargoStrategy {
    @Override
    public double calculateShipping(Order order) {
        double baseRate = 10.0; // Hızlı gönderim için temel ücret
        double weightFactor = 0.15; // Ağırlık başına ücret
        double distanceFactor = 0.1; // Mesafe başına ücret

        return baseRate + (order.getWeight() * weightFactor) + (order.getDistance() * distanceFactor);
    }
}

