package viewlayer;

import dataAccessLayer.DataSource;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import transferObjects.usersDTO;
import util.BCryptUtil;

/**
 *
 * @author MinhQuanNgo and phaml
 */
public class LoginServlet extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            response.getWriter().println("<p>No action provided.</p>");
            return;
        }

        switch (action.toLowerCase()) {
            case "login":
                handleLogin(request, response);
                break;
            default:
                response.getWriter().println("<p>Unknown action: " + action + "</p>");
                break;
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        try (Connection conn = DataSource.getConnection()) {

           PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE email = ? AND role = ? AND is_approved = TRUE");
           ps.setString(1, email);
           ps.setString(2, role);

           ResultSet rs = ps.executeQuery();

           if (rs.next()) {
               String hashedPasswordFromDB = rs.getString("password");
               if (BCryptUtil.checkPassword(password, hashedPasswordFromDB)) {
                    HttpSession session = request.getSession();

                    // Create and store full user object
                    usersDTO user = new usersDTO();
                    user.setId(rs.getInt("user_id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));

                    session.setAttribute("user", user); 

           

                if ("Manager".equalsIgnoreCase(role)) {
                        response.sendRedirect(request.getContextPath() + "/dashboard/managerDashboard.jsp");
                   } else {
                        response.sendRedirect(request.getContextPath() + "/dashboard/operatorDashboard.jsp");
                   }
                 } else {
                     // incorrect password
                     request.setAttribute("error", "Invalid password.");
                     request.getRequestDispatcher("login/login.jsp").forward(request, response);
                 }
             } else {
                 request.setAttribute("error", "Account not found or not approved.");
                 request.getRequestDispatcher("login/login.jsp").forward(request, response);
             }


        } catch (SQLException e) {
            throw new ServletException("Login failed", e);
        }
    }

   
}
