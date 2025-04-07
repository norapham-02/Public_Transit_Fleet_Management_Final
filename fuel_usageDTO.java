/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transferObjects;

/**
 *
 * @author donav
 */
public class fuel_usageDTO {
    private int id;
    private int vehicleId;
    private String date;
    private double fuelConsumed;
    private double mileage;
    private String vehicleNumber;   
    private String vehicleType;
    private double efficiency;
    private String fuelType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleId() {
        return vehicleId;
    }
    public String getFuelType() {
        return fuelType;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getDate() {
        return date;
    }
    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public double getEfficiency() {
    return efficiency;
}
    public void setDate(String date) {
        this.date = date;
    }

    public double getFuelConsumed() {
        return fuelConsumed;
    }

    public void setFuelConsumed(double fuelConsumed) {
        this.fuelConsumed = fuelConsumed;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }
    public void setEfficiency(double efficiency) {
    this.efficiency = efficiency;
}
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
    
}
