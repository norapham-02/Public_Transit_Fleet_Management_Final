package viewlayer;

import dataAccessLayer.GpsTrackingDAO;
import dataAccessLayer.GpsTrackingDAOImpl;
import transferObjects.gps_trackingDTO;
import transferObjects.usersDTO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author MinhQuanNgo
 */
public class ViewGpsReportServlet extends HttpServlet {

    private final GpsTrackingDAO dao = new GpsTrackingDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        usersDTO user = (usersDTO) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login/login.jsp");
            return;
        }

        List<gps_trackingDTO> logs;

        if ("Manager".equalsIgnoreCase(user.getRole())) {
            logs = dao.findAllLogs();  // Manager sees all logs
        } else if ("Operator".equalsIgnoreCase(user.getRole())) {
            logs = dao.findLogsByOperator(user.getEmail()); // Operator sees their own
        } else {
            response.getWriter().println("Unauthorized role.");
            return;
        }

        request.setAttribute("gpsLogs", logs);
        request.getRequestDispatcher("features/vehicleGpsReport.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Displays GPS logs to Managers or Operators";
    }
}
