package AbstractFactoryPattern.Components.GpuPackage;


import CompositePattern.Hardware;

public interface Gpu extends Hardware {
    String getModel();
    int getMemorySize();         // VRAM in GB
    double getClockSpeed();      // Core clock speed in GHz
    String getFullSpecs();       // Detailed specs
}
