package dataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import transferObjects.maintenance_logDTO;

/**
 * Implementation of the Maintenance_logDAO interface. This class provides the 
 * database operations related to the maintenance logs, including fetching, 
 * adding, updating, and deleting maintenance logs for vehicles.
 * 
 * @author Prince original author of code
 */
public class Maintenance_logDAOImpl implements Maintenance_logDAO {

    /**
     * Retrieves the maintenance history for a specific vehicle.
     * 
     * @param vehicleId The ID of the vehicle whose maintenance history is to be retrieved.
     * @return A list of maintenance logs for the specified vehicle.
     */
    @Override
    public List<maintenance_logDTO> getMaintenanceHistory(int vehicleId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<maintenance_logDTO> maintenanceLogs = new ArrayList<>();

        try {
            String sql = "SELECT * FROM maintenance_logs WHERE vehicle_id = ?";
            connection = DataSource.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, vehicleId);
            rs = ps.executeQuery();

            while (rs.next()) {
                maintenance_logDTO log = new maintenance_logDTO();
                log.setId(rs.getInt("id"));
                log.setVehicleId(rs.getInt("vehicle_id"));
                log.setServiceDate(rs.getString("service_date"));
                log.setServiceType(rs.getString("service_type"));
                log.setMileageAtService(rs.getDouble("mileage_at_service"));
                log.setNextServiceDue(rs.getString("next_service_due"));
                maintenanceLogs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, ps, connection);
        }
        return maintenanceLogs;
    }

    /**
     * Retrieves a list of upcoming maintenance logs that are scheduled for vehicles.
     * 
     * @return A list of upcoming maintenance logs.
     */
    @Override
    public List<maintenance_logDTO> getUpcomingMaintenance() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<maintenance_logDTO> maintenanceLogs = new ArrayList<>();

        try {
            String sql = "SELECT m.*, v.vehicle_number " +
                         "FROM maintenance_logs m " +
                         "JOIN vehicles v ON m.vehicle_id = v.vehicle_id " +
                         "WHERE m.next_service_due IS NOT NULL " +
                         "ORDER BY m.next_service_due ASC";

            connection = DataSource.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                maintenance_logDTO log = new maintenance_logDTO();
                log.setId(rs.getInt("log_id"));
                log.setVehicleId(rs.getInt("vehicle_id"));
                log.setServiceDate(rs.getString("service_date"));
                log.setServiceType(rs.getString("service_type"));
                log.setMileageAtService(rs.getDouble("mileage_at_service"));
                log.setNextServiceDue(rs.getString("next_service_due"));
                log.setVehicleNumber(rs.getString("vehicle_number"));
                maintenanceLogs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, ps, connection);
        }
        return maintenanceLogs;
    }

    /**
     * Deletes a specific maintenance log from the database.
     * 
     * @param maintenanceLog The maintenance log to be deleted.
     */
    @Override
    public void deleteMaintenance(maintenance_logDTO maintenanceLog) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "DELETE FROM maintenance_logs WHERE id = ?";
            connection = DataSource.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, maintenanceLog.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(null, ps, connection);
        }
    }

    /**
     * Updates an existing maintenance log in the database.
     * 
     * @param maintenanceLog The maintenance log with updated information.
     */
    @Override
    public void updateMaintenance(maintenance_logDTO maintenanceLog) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "UPDATE maintenance_logs SET vehicle_id = ?, service_date = ?, service_type = ?, mileage_at_service = ?, next_service_due = ? WHERE id = ?";
            connection = DataSource.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, maintenanceLog.getVehicleId());
            ps.setString(2, maintenanceLog.getServiceDate());
            ps.setString(3, maintenanceLog.getServiceType());
            ps.setDouble(4, maintenanceLog.getMileageAtService());
            ps.setString(5, maintenanceLog.getNextServiceDue());
            ps.setInt(6, maintenanceLog.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(null, ps, connection);
        }
    }

    /**
     * Adds a new maintenance log to the database.
     * 
     * @param maintenanceLog The new maintenance log to be added.
     */
    @Override
    public void addMaintenanceLog(maintenance_logDTO maintenanceLog) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO maintenance_logs (vehicle_id, service_date, service_type, mileage_at_service, next_service_due) VALUES (?, ?, ?, ?, ?)";
            connection = DataSource.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, maintenanceLog.getVehicleId());
            ps.setString(2, maintenanceLog.getServiceDate());
            ps.setString(3, maintenanceLog.getServiceType());
            ps.setDouble(4, maintenanceLog.getMileageAtService());
            ps.setString(5, maintenanceLog.getNextServiceDue());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(null, ps, connection);
        }
    }

    /**
     * Retrieves all maintenance logs from the database.
     * 
     * @return A list of all maintenance logs.
     */
    @Override
    public List<maintenance_logDTO> getAllMaintenanceLogs() {
        List<maintenance_logDTO> maintenanceLogs = new ArrayList<>();
        String sql = "SELECT * FROM maintenance_logs";

        try (Connection connection = DataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                maintenance_logDTO log = new maintenance_logDTO();
                log.setId(rs.getInt("log_id"));
                log.setVehicleId(rs.getInt("vehicle_id"));
                log.setServiceDate(rs.getString("service_date"));
                log.setServiceType(rs.getString("service_type"));
                log.setMileageAtService(rs.getDouble("mileage_at_service"));
                log.setNextServiceDue(rs.getString("next_service_due"));
                maintenanceLogs.add(log);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maintenanceLogs;
    }

    /**
     * Helper method to close database resources (ResultSet, PreparedStatement, and Connection).
     * 
     * @param rs The ResultSet to be closed.
     * @param ps The PreparedStatement to be closed.
     * @param connection The Connection to be closed.
     */
    private void closeResources(ResultSet rs, PreparedStatement ps, Connection connection) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
