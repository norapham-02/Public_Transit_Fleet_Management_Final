package observer;

import transferObjects.fuel_usageDTO;

public interface FuelObserver {
    void update(fuel_usageDTO fuelLog);
}
