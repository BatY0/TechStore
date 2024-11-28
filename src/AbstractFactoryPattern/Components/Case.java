package AbstractFactoryPattern.Components;

import CompositePattern.Hardware;


public interface Case extends Hardware {
    enum CaseType {
        ATX_FULL_TOWER,
        ATX_MID_TOWER,
        MICRO_ATX,
        MINI_ITX
    }
    String getModel();
    CaseType getCaseType();
    int getFanCount();
}
