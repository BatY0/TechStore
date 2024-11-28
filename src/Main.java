import AbstractFactoryPattern.Factories.ASUSManufacturer;
import AbstractFactoryPattern.Components.Gpu;
import AbstractFactoryPattern.Factories.MSIManufacturer;
import CompositePattern.HardwareStock;

public class Main {
    public static void main(String[] args) {
        ASUSManufacturer asusFactory = new ASUSManufacturer();
        MSIManufacturer msiFactory = new MSIManufacturer();
        HardwareStock gpuStock = new HardwareStock();

        // Add 5 RTX3060 GPUs to the stock
        Gpu gpu = asusFactory.createGpu("RTX3060");
        gpuStock.add(gpu, 5);
        gpuStock.add(gpu, 10);
        gpu = msiFactory.createGpu("RTX3060");

        // Add 5 more RTX3060 GPUs to the stock
        gpuStock.add(gpu, 5);

        gpu = msiFactory.createGpu("RTX4060");
        gpuStock.add(gpu, 5);

        // Print the stock description and total price
        System.out.println(gpuStock.getDescription());
        System.out.println("Total Price: $" + gpuStock.getPrice());

        // Remove 3 RTX3060 GPUs from the stock
        gpuStock.remove(gpu, 3);

        // Print the stock description and total price after removal
        System.out.println(gpuStock.getDescription());
        System.out.println("Total Price: $" + gpuStock.getPrice());
    }
}
