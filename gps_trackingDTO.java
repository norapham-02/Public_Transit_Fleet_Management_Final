package transferObjects;

import java.time.LocalDateTime;

public class gps_trackingDTO {
    private int id;
    private String vehicleNumber;
    private String stationName;
    private String status;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private String loggedBy;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

    public String getStationName() { return stationName; }
    public void setStationName(String stationName) { this.stationName = stationName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }

    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }

    public String getLoggedBy() { return loggedBy; }
    public void setLoggedBy(String loggedBy) { this.loggedBy = loggedBy; }
}
