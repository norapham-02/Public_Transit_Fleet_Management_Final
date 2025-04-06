package observer;

import transferObjects.fuel_usageDTO;
import java.util.ArrayList;
import java.util.List;

public class FuelUsageSubject {
    private final List<FuelUsageObserver> observers = new ArrayList<>();

    public void registerObserver(FuelUsageObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(FuelUsageObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(fuel_usageDTO usage) {
        for (FuelUsageObserver observer : observers) {
            observer.update(usage);
        }
    }
}
