package dataAccessLayer;

import java.util.List;
import transferObjects.maintenance_logDTO;

/**
 * This interface defines the operations that can be performed on maintenance logs
 * in the system. These operations include retrieving, adding, updating, and deleting
 * maintenance logs for vehicles.
 * 
 * @author Prince original author of code
 */
public interface Maintenance_logDAO {

    /**
     * Retrieves the maintenance history for a specific vehicle.
     * 
     * @param vehicleId The ID of the vehicle whose maintenance history is to be retrieved.
     * @return A list of maintenance logs for the specified vehicle.
     */
    List<maintenance_logDTO> getMaintenanceHistory(int vehicleId);

    /**
     * Retrieves a list of upcoming maintenance logs that are scheduled to take place.
     * 
     * @return A list of maintenance logs that represent upcoming maintenance for vehicles.
     */
    List<maintenance_logDTO> getUpcomingMaintenance();

    /**
     * Deletes a specific maintenance log.
     * 
     * @param maintenanceLog The maintenance log to be deleted.
     * @throws SQLException If a database error occurs during the deletion.
     */
    void deleteMaintenance(maintenance_logDTO maintenanceLog);
    
    /**
     * Updates an existing maintenance log with new information.
     * 
     * @param maintenanceLog The maintenance log with updated information.
     * @throws SQLException If a database error occurs during the update.
     */
    void updateMaintenance(maintenance_logDTO maintenanceLog);
    
    /**
     * Adds a new maintenance log to the system.
     * 
     * @param maintenanceLog The new maintenance log to be added.
     * @throws SQLException If a database error occurs during the insertion.
     */
    void addMaintenanceLog(maintenance_logDTO maintenanceLog);
    
    /**
     * Retrieves all maintenance logs from the system.
     * 
     * @return A list of all maintenance logs.
     */
    List<maintenance_logDTO> getAllMaintenanceLogs(); 
}
