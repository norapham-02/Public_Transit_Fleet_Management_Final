package viewlayer.admin;

import dataAccessLayer.DataSource;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

/**
 *
 * @author MinhQuanNgo
 */
public class ApproveUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userIdStr = request.getParameter("id");

        if (userIdStr == null) {
            response.getWriter().println("<p>User ID not provided.</p>");
            return;
        }

        try (Connection conn = DataSource.getConnection()) {
            int userId = Integer.parseInt(userIdStr);

            PreparedStatement ps = conn.prepareStatement(
                "UPDATE users SET is_approved = TRUE WHERE user_id = ?"
            );
            ps.setInt(1, userId);

            int updated = ps.executeUpdate();

            if (updated > 0) {
                response.sendRedirect(request.getContextPath() + "/pendingUsers.jsp");
            } else {
                response.getWriter().println("<p>Approval failed. User not found.</p>");
            }

        } catch (Exception e) {
            throw new ServletException("Error approving user", e);
        }
    }
}
