package dataAccessLayer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * A utility class for obtaining database connections.
 * The class loads database connection properties from a properties file and provides
 * a static method to establish a connection to the database.
 * 
 * This class cannot be instantiated.
 * 
 * @author Prince original author of code
 */
public class DataSource {

    // Private constructor to prevent instantiation
    private DataSource() {
        // Utility class: no instantiation
    }

    // Path to the properties file containing the database connection details
    private static final String PROPERTIES_FILE = "/database.properties";

    /**
     * Retrieves a database connection using the details specified in the properties file.
     * 
     * @return a Connection object to the database
     * @throws RuntimeException if there is an error loading properties or establishing the connection
     */
    public static Connection getConnection() {
        try (InputStream input = DataSource.class.getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                // If the properties file cannot be found, throw an exception
                throw new RuntimeException("Unable to find " + PROPERTIES_FILE);
            }

            // Load the properties from the file
            Properties props = new Properties();
            props.load(input);

            // Retrieve the database connection properties
            String url = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");

            // Load the MySQL JDBC driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Return a connection to the database
            return DriverManager.getConnection(url, username, password);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            // Handle exceptions and throw a runtime exception with a descriptive message
            throw new RuntimeException("Database connection error: " + e.getMessage(), e);
        }
    }
}
