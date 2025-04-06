package dataAccessLayer;

import transferObjects.gps_trackingDTO;
import java.sql.*;
import java.util.*;
import transferObjects.OperatorPerformanceDTO;

/**
 * Data Access Object (DAO) implementation for GPS tracking logs.
 * This class provides methods for interacting with the database to manage GPS tracking logs,
 * including inserting, retrieving logs by various parameters, and calculating operator performance.
 * 
 * @author Prince original author of code
 */
public class GpsTrackingDAOImpl implements GpsTrackingDAO {

    /**
     * Inserts a new GPS tracking log into the database.
     * 
     * @param log the gps_trackingDTO object containing the GPS log details to be inserted
     */
    @Override
    public void insert(gps_trackingDTO log) {
        try (Connection conn = DataSource.getConnection()) {
            String sql = "INSERT INTO gps_tracking (vehicle_number, station_name, arrival_time, departure_time, status, logged_by) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, log.getVehicleNumber());
            ps.setString(2, log.getStationName());
            ps.setObject(3, log.getArrivalTime());
            ps.setObject(4, log.getDepartureTime());
            ps.setString(5, log.getStatus());
            ps.setString(6, log.getLoggedBy());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves GPS tracking logs by the operator's identifier (email).
     * 
     * @param loggedBy the operator's email to filter the logs
     * @return a list of gps_trackingDTO objects containing the GPS logs for the specified operator
     */
    @Override
    public List<gps_trackingDTO> findLogsByOperator(String loggedBy) {
        List<gps_trackingDTO> logs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DataSource.getConnection();
            String sql = "SELECT * FROM gps_tracking WHERE logged_by = ? ORDER BY arrival_time DESC";
            ps = conn.prepareStatement(sql);
            ps.setString(1, loggedBy);

            rs = ps.executeQuery();

            while (rs.next()) {
                gps_trackingDTO log = new gps_trackingDTO();
                log.setId(rs.getInt("id"));
                log.setVehicleNumber(rs.getString("vehicle_number"));
                log.setStationName(rs.getString("station_name"));
                log.setStatus(rs.getString("status"));

                Timestamp arrivalTs = rs.getTimestamp("arrival_time");
                if (arrivalTs != null) {
                    log.setArrivalTime(arrivalTs.toLocalDateTime());
                }

                Timestamp departureTs = rs.getTimestamp("departure_time");
                if (departureTs != null) {
                    log.setDepartureTime(departureTs.toLocalDateTime());
                }

                log.setLoggedBy(rs.getString("logged_by"));
                logs.add(log);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return logs;
    }

    /**
     * Retrieves all GPS tracking logs from the database.
     * 
     * @return a list of all gps_trackingDTO objects from the database
     */
    @Override
    public List<gps_trackingDTO> findAllLogs() {
        List<gps_trackingDTO> list = new ArrayList<>();
        try (Connection conn = DataSource.getConnection()) {
            String sql = "SELECT * FROM gps_tracking ORDER BY log_time DESC";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Retrieves GPS tracking logs based on the vehicle number.
     * 
     * @param vehicleNumber the vehicle number to filter the logs
     * @return a list of gps_trackingDTO objects containing the GPS logs for the specified vehicle
     */
    @Override
    public List<gps_trackingDTO> findLogsByVehicle(String vehicleNumber) {
        List<gps_trackingDTO> list = new ArrayList<>();
        try (Connection conn = DataSource.getConnection()) {
            String sql = "SELECT * FROM gps_tracking WHERE vehicle_number = ? ORDER BY arrival_time DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, vehicleNumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Retrieves GPS tracking logs based on the user's email.
     * 
     * @param email the user's email to filter the logs
     * @return a list of gps_trackingDTO objects containing the GPS logs for the specified user
     */
    @Override
    public List<gps_trackingDTO> findLogsByUser(String email) {
        List<gps_trackingDTO> list = new ArrayList<>();
        try (Connection conn = DataSource.getConnection()) {
            String sql = "SELECT * FROM gps_tracking WHERE logged_by = ? ORDER BY log_time DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Maps a ResultSet to a gps_trackingDTO object.
     * 
     * @param rs the ResultSet containing the GPS tracking log data
     * @return a gps_trackingDTO object mapped from the ResultSet
     * @throws SQLException if an error occurs while accessing the ResultSet
     */
    private gps_trackingDTO map(ResultSet rs) throws SQLException {
        gps_trackingDTO log = new gps_trackingDTO();
        log.setId(rs.getInt("id"));
        log.setVehicleNumber(rs.getString("vehicle_number"));
        log.setStationName(rs.getString("station_name"));
        log.setArrivalTime(rs.getTimestamp("arrival_time") != null ? rs.getTimestamp("arrival_time").toLocalDateTime() : null);
        log.setDepartureTime(rs.getTimestamp("departure_time") != null ? rs.getTimestamp("departure_time").toLocalDateTime() : null);
        log.setStatus(rs.getString("status"));
        log.setLoggedBy(rs.getString("logged_by"));
        return log;
    }

    /**
     * Adds a new GPS tracking log to the database.
     * 
     * @param log the gps_trackingDTO object containing the GPS log details to be added
     */
    @Override
    public void addGpsLog(gps_trackingDTO log) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DataSource.getConnection();

            String sql = "INSERT INTO gps_tracking " +
                         "(vehicle_number, station_name, status, arrival_time, departure_time, logged_by) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, log.getVehicleNumber());
            ps.setString(2, log.getStationName());
            ps.setString(3, log.getStatus());

            if (log.getArrivalTime() != null) {
                ps.setTimestamp(4, java.sql.Timestamp.valueOf(log.getArrivalTime()));
            } else {
                ps.setNull(4, java.sql.Types.TIMESTAMP);
            }

            if (log.getDepartureTime() != null) {
                ps.setTimestamp(5, java.sql.Timestamp.valueOf(log.getDepartureTime()));
            } else {
                ps.setNull(5, java.sql.Types.TIMESTAMP);
            }

            ps.setString(6, log.getLoggedBy());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retrieves performance data for all operators, including the total number of logs and out-of-service logs.
     * 
     * @return a list of OperatorPerformanceDTO objects containing the operator performance data
     */
    public List<OperatorPerformanceDTO> getOperatorPerformance() {
        List<OperatorPerformanceDTO> list = new ArrayList<>();
        String sql = "SELECT logged_by, COUNT(*) AS total_logs, " +
                     "SUM(CASE WHEN status = 'OUT_OF_SERVICE' THEN 1 ELSE 0 END) AS out_of_service " +
                     "FROM gps_tracking GROUP BY logged_by";

        try (Connection conn = DataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                OperatorPerformanceDTO dto = new OperatorPerformanceDTO();
                dto.setOperatorEmail(rs.getString("logged_by"));
                dto.setTotalLogs(rs.getInt("total_logs"));
                dto.setOutOfServiceLogs(rs.getInt("out_of_service"));
                list.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
