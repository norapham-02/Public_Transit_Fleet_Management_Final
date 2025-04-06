package observer;

import transferObjects.fuel_usageDTO;

public class FuelAlertObserver implements FuelObserver {

    private final double threshold;

    public FuelAlertObserver(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public void update(fuel_usageDTO fuelLog) {
        if (fuelLog.getFuelConsumed() > threshold) {
            System.out.println("🚨 Fuel Alert: Vehicle " + fuelLog.getVehicleNumber()
                    + " consumed " + fuelLog.getFuelConsumed() + "L on " + fuelLog.getDate());
        }
    }
}
