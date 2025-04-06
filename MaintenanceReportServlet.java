package viewlayer;

import dataAccessLayer.Maintenance_logDAO;
import dataAccessLayer.Maintenance_logDAOImpl;
import transferObjects.maintenance_logDTO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 *
 * @author MinhQuanNgo
 */

public class MaintenanceReportServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            response.setContentType("text/html;charset=UTF-8");
             PrintWriter out = response.getWriter();

             try {
                 out.println("<!DOCTYPE html>");
                 out.println("<html>");
                 out.println("<head>");
                 out.println("<title>Upcoming Maintenance Report</title>");
                 out.println("</head>");
                 out.println("<body bgcolor='#FDF5E6'>");

                 out.println("<h1 align='center'>Upcoming Maintenance</h1>");
                 out.println("<hr width='60%'>");

                 out.println("<div align='center'>");

                 Maintenance_logDAO dao = new Maintenance_logDAOImpl();
                 List<maintenance_logDTO> maintenanceList = dao.getUpcomingMaintenance();

                 if (maintenanceList != null && !maintenanceList.isEmpty()) {
                     out.println("<table border='1' cellpadding='8' cellspacing='0'>");
                     out.println("<thead bgcolor='#ffe4b5'>");
                     out.println("<tr>");
                     out.println("<th>Vehicle Number</th>");
                     out.println("<th>Service Type</th>");
                     out.println("<th>Service Date</th>");
                     out.println("<th>Mileage at Service</th>");
                     out.println("<th>Next Service Due</th>");
                     out.println("</tr>");
                     out.println("</thead>");
                     out.println("<tbody>");

                     for (maintenance_logDTO log : maintenanceList) {
                         out.printf("<tr>"
                                 + "<td>%s</td>"
                                 + "<td>%s</td>"
                                 + "<td>%s</td>"
                                 + "<td>%.1f km</td>"
                                 + "<td>%s</td>"
                                 + "</tr>",
                                 log.getVehicleNumber(),
                                 log.getServiceType(),
                                 log.getServiceDate(),
                                 log.getMileageAtService(),
                                 log.getNextServiceDue());
                     }

                     out.println("</tbody>");
                     out.println("</table>");
                 } else {
                     out.println("<p style='color:gray;'>No upcoming maintenance scheduled.</p>");
                 }

                 out.println("<br>");
                 out.printf("<a href='%s/dashboard/managerDashboard.jsp'>⬅ Back to Dashboard</a>",
                         request.getContextPath());

                 out.println("</div>");
                 out.println("</body>");
                 out.println("</html>");
             } catch (Exception e) {
                 out.println("<p style='color:red;'>❌ Error loading maintenance report: " + e.getMessage() + "</p>");
             } finally {
                 out.close();
             }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Displays upcoming maintenance in HTML table format.";
    }
}
