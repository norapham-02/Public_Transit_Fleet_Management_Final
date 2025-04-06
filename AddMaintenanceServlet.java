package viewlayer;

import dataAccessLayer.Maintenance_logDAO;
import dataAccessLayer.Maintenance_logDAOImpl;
import transferObjects.maintenance_logDTO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


public class AddMaintenanceServlet extends HttpServlet {

    /**
     * Show maintenance scheduling form
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Schedule Maintenance</title></head><body>");
            out.println("<h1>Schedule Maintenance</h1>");
            out.println("<form method='post' action='AddMaintenanceServlet'>");

            out.println("Vehicle ID: <input type='number' name='vehicleId' required><br><br>");
            out.println("Service Date: <input type='date' name='serviceDate' required><br><br>");
            out.println("Service Type: <input type='text' name='serviceType' required><br><br>");
            out.println("Mileage at Service: <input type='number' step='0.1' name='mileageAtService' required><br><br>");
            out.println("Next Service Due: <input type='date' name='nextServiceDue'><br><br>");

            out.println("<input type='submit' value='Schedule'>");
            out.println("</form>");
            out.println("<p><a href='GetAllMaintenanceServlet'>Back to Maintenance List</a></p>");
            out.println("</body></html>");
        } finally {
            out.close();
        }
    }

    /**
     * Handle maintenance form submission
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
            String serviceDate = request.getParameter("serviceDate");
            String serviceType = request.getParameter("serviceType");
            double mileage = Double.parseDouble(request.getParameter("mileageAtService"));
            String nextService = request.getParameter("nextServiceDue");

            // DTO
            maintenance_logDTO log = new maintenance_logDTO();
            log.setVehicleId(vehicleId);
            log.setServiceDate(serviceDate);
            log.setServiceType(serviceType);
            log.setMileageAtService(mileage);
            log.setNextServiceDue(nextService);

            // DAO
            Maintenance_logDAO dao = new Maintenance_logDAOImpl();
            dao.addMaintenanceLog(log);

            // Confirmation
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Success</title></head><body>");
            out.println("<h2>✅ Maintenance scheduled successfully!</h2>");
            out.println("<p><a href='GetAllMaintenanceServlet'>View Maintenance</a></p>");
            out.println("<p><a href='AddMaintenanceServlet'>Schedule Another</a></p>");
            out.println("</body></html>");

        } catch (Exception e) {
            out.println("<p style='color:red;'>❌ Error: " + e.getMessage() + "</p>");
        } finally {
            out.close();
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for scheduling vehicle maintenance";
    }
}
