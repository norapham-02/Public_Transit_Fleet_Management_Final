package transferObjects;

import java.sql.Date;

public class vehiclesDTO {
    private Integer id;
    private String vehicleType;
    private String vehicleNumber;
    private String fuelType;
    private double consumptionRate;
    private int maxPassengers;
    private int assignedRoute;
    private Date lastServiceDate;

    // Constructor 
    private vehiclesDTO(Builder builder) {
        this.id = builder.id;
        this.vehicleType = builder.vehicleType;
        this.vehicleNumber = builder.vehicleNumber;
        this.fuelType = builder.fuelType;
        this.consumptionRate = builder.consumptionRate;
        this.maxPassengers = builder.maxPassengers;
        this.assignedRoute = builder.assignedRoute;
        this.lastServiceDate = builder.lastServiceDate;
    }

    // Static Builder class
    public static class Builder {
        private int id;
        private String vehicleType;
        private String vehicleNumber;
        private String fuelType;
        private double consumptionRate;
        private int maxPassengers;
        private int assignedRoute;
        private Date lastServiceDate;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setVehicleType(String vehicleType) {
            this.vehicleType = vehicleType;
            return this;
        }

        public Builder setVehicleNumber(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
            return this;
        }

        public Builder setFuelType(String fuelType) {
            this.fuelType = fuelType;
            return this;
        }

        public Builder setConsumptionRate(double rate) {
            this.consumptionRate = rate;
            return this;
        }

        public Builder setMaxPassengers(int passengers) {
            this.maxPassengers = passengers;
            return this;
        }

        public Builder setAssignedRoute(int route) {
            this.assignedRoute = route;
            return this;
        }

        public Builder setLastServiceDate(Date lastServiceDate) {
            this.lastServiceDate = lastServiceDate;
            return this;
        }

        // build()
        public vehiclesDTO build() {
            return new vehiclesDTO(this);
        }
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getConsumptionRate() {
        return consumptionRate;
    }

    public void setConsumptionRate(double consumptionRate) {
        this.consumptionRate = consumptionRate;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public int getAssignedRoute() {
        return assignedRoute;
    }

    public void setAssignedRoute(int assignedRoute) {
        this.assignedRoute = assignedRoute;
    }

    public Date getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(Date lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }
}
