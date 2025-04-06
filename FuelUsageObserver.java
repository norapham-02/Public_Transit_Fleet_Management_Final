package observer;

import transferObjects.fuel_usageDTO;

public interface FuelUsageObserver {
    void update(fuel_usageDTO usage);
}
