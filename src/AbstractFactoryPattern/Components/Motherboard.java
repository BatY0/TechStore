package AbstractFactoryPattern.Components;

import CompositePattern.Hardware;

public interface Motherboard extends Hardware {
    String getModel();
    SocketType getSocketType();
    int getRamSlotCount();

}
