/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

import dataAccessLayer.VehicleDAO;
import dataAccessLayer.VehicleDAOImpl;
import java.sql.SQLException;
import transferObjects.vehiclesDTO;

import java.util.List;

/**
 * Business logic layer for handling vehicle-related operations.
 */
public class VehicleBusinessLogic {
    private VehicleDAO vehicleDAO;

    public VehicleBusinessLogic() {
        // You can later use dependency injection if needed
        this.vehicleDAO = new VehicleDAOImpl();
    }

    /**
     * Retrieves a list of all vehicles from the database.
     * 
     * @return List of vehicleDTO objects
     */
    public List<vehiclesDTO> getAllVehicles() throws SQLException {
        return vehicleDAO.getAllVehicles();
    }
}