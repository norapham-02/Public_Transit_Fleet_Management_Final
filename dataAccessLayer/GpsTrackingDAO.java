package dataAccessLayer;

import transferObjects.gps_trackingDTO;
import java.util.List;
import transferObjects.OperatorPerformanceDTO;

/**
 * Interface for data access operations related to GPS tracking logs.
 * This interface defines methods for adding, retrieving, and processing GPS tracking logs.
 * 
 * @author Prince original author of code
 */
public interface GpsTrackingDAO {

    /**
     * Adds a new GPS tracking log to the database.
     * 
     * @param log the gps_trackingDTO object containing the GPS log details to be added
     */
    void addGpsLog(gps_trackingDTO log);

    /**
     * Inserts a new GPS tracking log into the database.
     * 
     * @param log the gps_trackingDTO object containing the GPS log details to be inserted
     */
    void insert(gps_trackingDTO log);

    /**
     * Retrieves all GPS tracking logs for a specific operator based on the operator's identifier.
     * 
     * @param loggedBy the operator's identifier (email or name) to filter the logs
     * @return a list of gps_trackingDTO objects containing the GPS logs for the specified operator
     */
    List<gps_trackingDTO> findLogsByOperator(String loggedBy);

    /**
     * Retrieves all GPS tracking logs from the database.
     * 
     * @return a list of all gps_trackingDTO objects in the database
     */
    List<gps_trackingDTO> findAllLogs();

    /**
     * Retrieves GPS tracking logs based on the vehicle number.
     * 
     * @param vehicleNumber the vehicle number to filter the logs
     * @return a list of gps_trackingDTO objects containing the GPS logs for the specified vehicle
     */
    List<gps_trackingDTO> findLogsByVehicle(String vehicleNumber);

    /**
     * Retrieves GPS tracking logs based on the user's email.
     * 
     * @param email the user's email to filter the logs
     * @return a list of gps_trackingDTO objects containing the GPS logs for the specified user
     */
    List<gps_trackingDTO> findLogsByUser(String email);

    /**
     * Retrieves performance data for all operators.
     * 
     * @return a list of OperatorPerformanceDTO objects containing performance data for each operator
     */
    public List<OperatorPerformanceDTO> getOperatorPerformance();
}
