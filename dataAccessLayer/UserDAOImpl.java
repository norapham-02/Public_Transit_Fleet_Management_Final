package dataAccessLayer;

import transferObjects.usersDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of UserDAO interface for interacting with user data in the database.
 * Provides CRUD operations for user data.
 * 
 * @author Prince original author of code
 */
public class UserDAOImpl implements UserDAO {
    private final DataSource dataSource;

    /**
     * Constructor for UserDAOImpl. Initializes the DataSource from JNDI context.
     */
    public UserDAOImpl() {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDB"); // Match web.xml or server.xml
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize DataSource", e);
        }
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve.
     * @return a usersDTO object representing the user, or null if not found.
     */
    @Override
    public usersDTO getUserById(Integer userId) {
        usersDTO user = null;
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new usersDTO();
                    user.setId(rs.getInt("user_id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return a list of usersDTO objects representing all users.
     */
    @Override
    public List<usersDTO> getAllUsers() {
        List<usersDTO> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
                usersDTO user = new usersDTO();
                user.setId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Updates the information of an existing user.
     *
     * @param user the user object containing the updated information.
     */
    @Override
    public void updateUser(usersDTO user) {
        String sql = "UPDATE users SET name = ?, email = ?, role = ? WHERE user_id = ?";
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getRole());
            ps.setInt(4, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a user from the database.
     *
     * @param user the user object representing the user to be deleted.
     */
    @Override
    public void deleteUser(usersDTO user) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new user to the database.
     *
     * @param user the user object containing the user's information.
     */
    @Override
    public void addUser(usersDTO user) {
        String sql = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a list of users by their role.
     *
     * @param role the role to filter users by.
     * @return a list of usersDTO objects representing the users with the specified role.
     */
    @Override
    public List<usersDTO> getUsersByRole(String role) {
        List<usersDTO> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = ?";

        try (Connection connection = dataSource.getConnection();
                    PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, role);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usersDTO user = new usersDTO();
                user.setId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
