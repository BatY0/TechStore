package AbstractFactoryPattern.Components;

import CompositePattern.Hardware;

public interface CpuCooler extends Hardware {
    enum CoolingType{
        Liquid,
        Air
    }
    String getModel();
    CoolingType getCoolingType();
    int getFanSpeed();
    double getNoiseLevel();
    String getSize();
}
