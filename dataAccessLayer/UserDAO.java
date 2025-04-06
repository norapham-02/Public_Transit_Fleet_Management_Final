package dataAccessLayer;

import java.util.List;
import transferObjects.usersDTO;

/**
 * User Data Access Object Interface
 * Provides methods for interacting with user data in the database.
 * 
 * @author Prince original author of code
 */
public interface UserDAO {

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve.
     * @return a usersDTO object representing the user, or null if not found.
     */
    usersDTO getUserById(Integer userId);

    /**
     * Retrieves all users from the database.
     *
     * @return a list of usersDTO objects representing all users.
     */
    List<usersDTO> getAllUsers();

    /**
     * Updates the information of an existing user.
     *
     * @param user the user object containing the updated information.
     */
    void updateUser(usersDTO user);

    /**
     * Deletes a user from the database.
     *
     * @param user the user object representing the user to be deleted.
     */
    void deleteUser(usersDTO user);

    /**
     * Adds a new user to the database.
     *
     * @param user the user object containing the user's information.
     */
    void addUser(usersDTO user);

    /**
     * Retrieves a list of users by their role.
     *
     * @param operator the role to filter users by.
     * @return a list of usersDTO objects representing the users with the specified role.
     */
    public List<usersDTO> getUsersByRole(String operator);
}
