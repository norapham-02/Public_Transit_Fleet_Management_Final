package viewlayer;

import dataAccessLayer.DataSource;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import util.BCryptUtil;

/**
 *
 * @author MinhQuanNgo
 */
public class RegisterServlet extends HttpServlet {
    
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to register.jsp page
        request.getRequestDispatcher("login/register.jsp").forward(request, response);
}

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String password = request.getParameter("password");
        String hashedPassword = BCryptUtil.hashPassword(password);
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        try (Connection conn = DataSource.getConnection()) {

            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO users (name, email, password, role, is_approved) VALUES (?, ?, ?, ?, false)"
            );
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, hashedPassword);
            ps.setString(4, role);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                request.setAttribute("name", name);
                request.getRequestDispatcher("/registrationSuccess.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Registration failed.");
                request.getRequestDispatcher("login/register.jsp").forward(request, response);
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            // This means duplicate email (violates UNIQUE constraint)
            request.setAttribute("error", "This email is already registered.");
            request.getRequestDispatcher("login/register.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Registration failed", e);
        }
    }
}
