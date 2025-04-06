package dataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import transferObjects.vehiclesDTO;

/**
 * VehicleDAOImpl class implements the VehicleDAO interface and provides 
 * methods to interact with the database for vehicle-related operations.
 * It includes methods for adding, updating, retrieving, and deleting vehicles.
 *
 * @author Prince, original author of code
 */
public class VehicleDAOImpl implements VehicleDAO {

    /**
     * Adds a new vehicle to the database.
     * 
     * @param vehicle The vehicle object to be added.
     */
    @Override
    public void addVehicle(vehiclesDTO vehicle) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO vehicles (vehicle_type, vehicle_number, fuel_type, consumption_rate, max_passengers, assigned_route) VALUES(?, ?, ?, ?, ?, ?)";
            connection = DataSource.getConnection();
            ps = connection.prepareStatement(sql);

            // Set the parameters for the vehicle to be inserted into the database
            ps.setString(1, vehicle.getVehicleType());
            ps.setString(2, vehicle.getVehicleNumber());
            ps.setString(3, vehicle.getFuelType());
            ps.setDouble(4, vehicle.getConsumptionRate());
            ps.setInt(5, vehicle.getMaxPassengers());
            ps.setInt(6, vehicle.getAssignedRoute());

            ps.executeUpdate(); // Execute the insert query

        } catch (SQLException e) {
            e.printStackTrace(); // Print any SQL exceptions
        } finally {
            try {
                if (ps != null) {
                    ps.close(); // Close PreparedStatement
                }
                if (connection != null) {
                    connection.close(); // Close connection
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Retrieves a vehicle from the database by its ID.
     * 
     * @param vehicleId The ID of the vehicle to be retrieved.
     * @return The vehicle object corresponding to the provided ID, or null if not found.
     */
    @Override
    public vehiclesDTO getVehicleById(Integer vehicleId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        vehiclesDTO vehicle = null;

        try {
            String sql = "SELECT * FROM vehicles where vehicle_id = ?";
            connection = DataSource.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, vehicleId.intValue());
            rs = ps.executeQuery();

            // Process the result set and map the data to a vehiclesDTO object
            while (rs.next()) {
                vehicle = new vehiclesDTO.Builder()
                    .setVehicleType(rs.getString("vehicle_type"))
                    .setVehicleNumber(rs.getString("vehicle_number"))
                    .setFuelType(rs.getString("fuel_type"))
                    .setConsumptionRate(rs.getDouble("consumption_rate"))
                    .setMaxPassengers(rs.getInt("max_passengers"))
                    .setAssignedRoute(rs.getInt("assigned_route"))
                    .build();
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Print any SQL exceptions
        } finally {
            try {
                if (rs != null) {
                    rs.close(); // Close ResultSet
                }
                if (ps != null) {
                    ps.close(); // Close PreparedStatement
                }
                if (connection != null) {
                    connection.close(); // Close connection
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return vehicle; // Return the found vehicle or null
    }

    /**
     * Retrieves all vehicles from the database.
     * 
     * @return A list of all vehicles in the database.
     */
    @Override
    public List<vehiclesDTO> getAllVehicles() {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<vehiclesDTO> vehicles = new ArrayList<>();

        try {
            connection = DataSource.getConnection();
            String query = "SELECT * FROM vehicles ORDER BY vehicle_id";
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();

            // Iterate through the result set and build a list of vehiclesDTO objects
            while (rs.next()) {
                vehiclesDTO vehicle = new vehiclesDTO.Builder()
                    .setId(rs.getInt("vehicle_id"))
                    .setVehicleType(rs.getString("vehicle_type"))
                    .setVehicleNumber(rs.getString("vehicle_number"))
                    .setFuelType(rs.getString("fuel_type"))
                    .setConsumptionRate(rs.getDouble("consumption_rate"))
                    .setMaxPassengers(rs.getInt("max_passengers"))
                    .setAssignedRoute(rs.getInt("assigned_route"))
                    .setLastServiceDate(rs.getDate("last_service_date"))
                    .build();
                vehicles.add(vehicle); // Add vehicle to the list
            }
        } catch (SQLException e) {
            System.err.println("❌ Failed to retrieve vehicles: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close(); // Close ResultSet
                if (pstmt != null) pstmt.close(); // Close PreparedStatement
                if (connection != null) connection.close(); // Close connection
            } catch (SQLException e) {
                System.err.println("❌ Error closing resources: " + e.getMessage());
            }
        }

        return vehicles; // Return the list of vehicles
    }

    /**
     * Deletes a vehicle from the database by its ID.
     * 
     * @param vehicleId The ID of the vehicle to be deleted.
     */
    @Override
    public void deleteVehicle(int vehicleId) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "DELETE FROM vehicles WHERE vehicle_id = ?";
            connection = DataSource.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, vehicleId); // Set vehicle ID parameter for deletion
            ps.executeUpdate(); // Execute delete query
        } catch (SQLException e) {
            e.printStackTrace(); // Print any SQL exceptions
        } finally {
            try {
                if (ps != null) {
                    ps.close(); // Close PreparedStatement
                }
                if (connection != null) {
                    connection.close(); // Close connection
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Updates the details of an existing vehicle in the database.
     * 
     * @param vehicle The vehicle object containing the updated details.
     */
    @Override
    public void updateVehicle(vehiclesDTO vehicle) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DataSource.getConnection();
            String sql = "UPDATE vehicles SET vehicle_type = ?, vehicle_number = ?, fuel_type = ?, consumption_rate = ?, max_passengers = ?, assigned_route = ?, last_service_date = ? WHERE vehicle_id = ?";
            ps = con.prepareStatement(sql);

            // Set the parameters for the update query
            ps.setString(1, vehicle.getVehicleType());
            ps.setString(2, vehicle.getVehicleNumber());
            ps.setString(3, vehicle.getFuelType());
            ps.setDouble(4, vehicle.getConsumptionRate());
            ps.setInt(5, vehicle.getMaxPassengers());
            ps.setInt(6, vehicle.getAssignedRoute());
            ps.setDate(7, vehicle.getLastServiceDate());
            ps.setInt(8, vehicle.getId()); // Set vehicle ID for the update

            ps.executeUpdate(); // Execute the update query
        } catch (SQLException e) {
            e.printStackTrace(); // Print any SQL exceptions
        } finally {
            try {
                if (ps != null) {
                    ps.close(); // Close PreparedStatement
                }
                if (con != null) {
                    con.close(); // Close connection
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Finds a vehicle assigned to a specific operator by their email.
     * 
     * @param email The email of the operator.
     * @return The vehicle assigned to the operator, or null if not found.
     */
    public vehiclesDTO findVehicleByOperator(String email) {
        vehiclesDTO vehicle = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DataSource.getConnection();
            String sql = "SELECT * FROM vehicles WHERE assigned_operator_email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email); // Set the email of the operator
            rs = ps.executeQuery();

            // Process the result set and create a vehiclesDTO object
            if (rs.next()) {
                vehicle = new vehiclesDTO.Builder()
                        .setId(rs.getInt("vehicle_id"))
                        .setVehicleNumber(rs.getString("vehicle_number"))
                        .setAssignedRoute(rs.getInt("assigned_route"))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Print any SQL exceptions
        } finally {
            try {
                if (rs != null) rs.close(); // Close ResultSet
                if (ps != null) ps.close(); // Close PreparedStatement
                if (conn != null) conn.close(); // Close connection
            } catch (SQLException e) {
                e.printStackTrace(); // Print any SQL exceptions during cleanup
            }
        }

        return vehicle; // Return the found vehicle or null
    }

    /**
     * Assigns a vehicle to an operator by updating the vehicle's operator email.
     * 
     * @param vehicleId The ID of the vehicle.
     * @param operatorEmail The email of the operator to be assigned.
     */
    @Override
    public void assignVehicleToOperator(int vehicleId, String operatorEmail) {
        try (Connection conn = DataSource.getConnection()) {
            String sql = "UPDATE vehicles SET assigned_operator_email = ? WHERE vehicle_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, operatorEmail); // Set the operator's email
            ps.setInt(2, vehicleId); // Set the vehicle's ID
            ps.executeUpdate(); // Execute the update query
        } catch (SQLException e) {
            e.printStackTrace(); // Print any SQL exceptions
        }
    }
}
