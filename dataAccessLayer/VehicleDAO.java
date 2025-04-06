package dataAccessLayer;

import java.sql.SQLException;
import java.util.List;
import transferObjects.vehiclesDTO;

/**
 * Interface for interacting with vehicle data in the database.
 * Defines methods for CRUD operations related to vehicles.
 * 
 * @author Prince original author of code
 */
public interface VehicleDAO {
 
    /**
     * Adds a new vehicle to the database.
     *
     * @param vehicle the vehicle object containing the vehicle information.
     */
    void addVehicle(vehiclesDTO vehicle);
    
    /**
     * Retrieves a vehicle by its ID.
     *
     * @param vehicleId the ID of the vehicle to retrieve.
     * @return a vehiclesDTO object representing the vehicle, or null if not found.
     */
    vehiclesDTO getVehicleById(Integer vehicleId);
    
    /**
     * Retrieves all vehicles from the database.
     *
     * @return a list of vehiclesDTO objects representing all vehicles.
     */
    List<vehiclesDTO> getAllVehicles();
    
    /**
     * Updates the information of an existing vehicle.
     *
     * @param vehicle the vehicle object containing the updated information.
     */
    void updateVehicle(vehiclesDTO vehicle);
    
    /**
     * Deletes a vehicle from the database.
     *
     * @param vehicleId the ID of the vehicle to delete.
     */
    void deleteVehicle(int vehicleId);
    
    /**
     * Finds a vehicle assigned to a specific operator based on their email.
     *
     * @param email the email of the operator.
     * @return a vehiclesDTO object representing the vehicle, or null if not found.
     */
    vehiclesDTO findVehicleByOperator(String email);

    /**
     * Assigns a vehicle to a specific operator.
     *
     * @param vehicleId the ID of the vehicle to assign.
     * @param operatorEmail the email of the operator to assign the vehicle to.
     */
    void assignVehicleToOperator(int vehicleId, String operatorEmail);
}
