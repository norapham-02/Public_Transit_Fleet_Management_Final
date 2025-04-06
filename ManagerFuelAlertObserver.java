package observer;

import transferObjects.fuel_usageDTO;

public class ManagerFuelAlertObserver implements FuelUsageObserver {

    @Override
    public void update(fuel_usageDTO usage) {
        FuelAlertStorage.getInstance().addAlert(usage);
    }
}
