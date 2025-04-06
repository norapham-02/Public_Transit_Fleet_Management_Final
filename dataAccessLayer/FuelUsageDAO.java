package dataAccessLayer;

import transferObjects.fuel_usageDTO;
import java.sql.*;
import java.util.*;

import observer.FuelUsageSubject;
import observer.FuelUsageObserver;
import observer.ManagerFuelAlertObserver;

/**
 * The Data Access Object (DAO) for interacting with fuel usage data in the database.
 * This class provides methods for retrieving and adding fuel usage records, and notifying observers 
 * if the fuel consumption exceeds a certain threshold.
 * 
 * @author Prince original author of code
 */
public class FuelUsageDAO {
    
    private Connection conn;  // Database connection
    private final FuelUsageSubject fuelAlertSubject = new FuelUsageSubject();  // Subject for fuel usage alerts

    /**
     * Constructor that initializes the database connection and registers an observer for fuel usage alerts.
     * 
     * @throws RuntimeException if the database connection fails
     */
    public FuelUsageDAO() {
        try {
            conn = DataSource.getConnection();  // Get database connection
            fuelAlertSubject.registerObserver(new ManagerFuelAlertObserver());  // Register observer for fuel alert
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database connection", e);
        }
    }

    /**
     * Retrieves all fuel usage records from the database, including associated vehicle details.
     * 
     * @return a list of fuel_usageDTO objects representing fuel usage records
     * @throws SQLException if an error occurs while querying the database
     */
    public List<fuel_usageDTO> getAllFuelUsage() throws SQLException {
        List<fuel_usageDTO> list = new ArrayList<>();  // List to store fuel usage data

        // SQL query to fetch fuel usage records along with vehicle information
        String sql = "SELECT fu.*, v.vehicle_number, v.vehicle_type " +
                     "FROM fuel_usage fu " +
                     "JOIN vehicles v ON fu.vehicle_id = v.vehicle_id";

        // Create and execute the statement
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        // Process the result set and map it to fuel_usageDTO objects
        while (rs.next()) {
            fuel_usageDTO usage = new fuel_usageDTO();
            usage.setId(rs.getInt("fuel_id"));
            usage.setVehicleId(rs.getInt("vehicle_id"));
            usage.setVehicleNumber(rs.getString("vehicle_number"));
            usage.setVehicleType(rs.getString("vehicle_type"));
            usage.setDate(rs.getString("date"));
            usage.setFuelConsumed(rs.getDouble("fuel_consumed"));
            usage.setMileage(rs.getDouble("mileage"));

            // Notify observers if the fuel consumption exceeds the threshold
            if (usage.getFuelConsumed() > 50) {
                fuelAlertSubject.notifyObservers(usage);  // Notify the observers
            }

            list.add(usage);  // Add the usage data to the list
        }

        return list;  // Return the list of fuel usage records
    }

    /**
     * Adds a new fuel usage record to the database.
     * 
     * @param usage the fuel_usageDTO object containing the fuel usage data to be added
     * @throws SQLException if an error occurs while inserting the data into the database
     */
    public void addFuelUsage(fuel_usageDTO usage) throws SQLException {
        // SQL query to insert a new fuel usage record
        String sql = "INSERT INTO fuel_usage (vehicle_id, date, fuel_consumed, mileage) VALUES (?, ?, ?, ?)";
        
        // Prepare the statement and set the parameters
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, usage.getVehicleId());
        ps.setString(2, usage.getDate());
        ps.setDouble(3, usage.getFuelConsumed());
        ps.setDouble(4, usage.getMileage());

        // Execute the update
        ps.executeUpdate();
    }
}
