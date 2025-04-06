/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



/**
 *
 * @author phaml
 */
package businesslayer;

import dataAccessLayer.UserDAOImpl;
import dataAccessLayer.UserDAO;

import transferObjects.usersDTO;

public class UserBusinessLogic {

    private final UserDAO userDAO = new UserDAOImpl();

    /**
     * Verifies user login by checking email, password, and expected role.
     * 
     * @param email        User email
     * @param password     User password
     * @param expectedRole "Manager" or "Operator"
     * @return true if login is valid, false otherwise
     */
    public boolean loginUser(String email, String password, String expectedRole) {
        usersDTO user = getUserByEmail(email);
        if (user == null) return false;

        return user.getPassword().equals(password) &&
               user.getRole().equalsIgnoreCase(expectedRole);
    }

    /**
     * Gets the user's role based on email. Optional utility method.
     * 
     * @param email user email
     * @return role (Manager or Operator) or null if not found
     */
    public String getUserRole(String email) {
        usersDTO user = getUserByEmail(email);
        return (user != null) ? user.getRole() : null;
    }

    /**
     * Helper method to find a user by email. Not in original DAO — you might want to add this in UserDAOImpl.
     * 
     * @param email the email to search for
     * @return usersDTO or null
     */
    private usersDTO getUserByEmail(String email) {
        for (usersDTO user : userDAO.getAllUsers()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }
}