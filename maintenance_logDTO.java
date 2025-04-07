/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transferObjects;

/**
 *
 * @author donav
 */
public class maintenance_logDTO {
    private Integer id;
    private int vehicleId;
    private String serviceDate;
    private String serviceType;
    private Double mileageAtService;
    private String nextServiceDue;
    private String vehicleNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Double getMileageAtService() {
        return mileageAtService;
    }

    public void setMileageAtService(Double mileageAtService) {
        this.mileageAtService = mileageAtService;
    }

    public String getNextServiceDue() {
        return nextServiceDue;
    }

    public void setNextServiceDue(String nextServiceDue) {
        this.nextServiceDue = nextServiceDue;
    }
    public String getVehicleNumber() {
    return vehicleNumber;
}
    public void setVehicleNumber(String vehicleNumber) {
    this.vehicleNumber = vehicleNumber;
}
    
    
}
