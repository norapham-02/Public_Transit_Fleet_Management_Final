package observer;

import transferObjects.fuel_usageDTO;
import java.util.ArrayList;
import java.util.List;

public class FuelAlertStorage {
    private static final FuelAlertStorage instance = new FuelAlertStorage();
    private final List<fuel_usageDTO> alerts = new ArrayList<>();

    private FuelAlertStorage() {}

    public static FuelAlertStorage getInstance() {
        return instance;
    }

    public void addAlert(fuel_usageDTO usage) {
        alerts.add(usage);
    }

    public List<fuel_usageDTO> getAlerts() {
        return alerts;
    }

    public void clear() {
        alerts.clear();
    }
}
