package viewlayer;

import dataAccessLayer.VehicleDAO;
import dataAccessLayer.VehicleDAOImpl;
import transferObjects.vehiclesDTO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 *
 * @author phaml
 */
public class AddVehicleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<p>🚫 This servlet only supports POST. Please submit the form properly.</p>");
        response.getWriter().println("<a href='dashboard/managerDashboard.jsp'>Back to Dashboard</a>");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve form parameters
        String vehicleType = request.getParameter("vehicleType");
        String vehicleNumber = request.getParameter("vehicleNumber");
        String fuelType = request.getParameter("fuelType");
        float consumptionRate = Float.parseFloat(request.getParameter("consumptionRate"));
        int maxPassengers = Integer.parseInt(request.getParameter("maxPassengers"));
        int assignedRoute = Integer.parseInt(request.getParameter("assignedRoute"));

        // Create DTO
        vehiclesDTO vehicle = new vehiclesDTO.Builder()
    .setVehicleType(vehicleType)
    .setVehicleNumber(vehicleNumber)
    .setFuelType(fuelType)
    .setConsumptionRate(consumptionRate)
    .setMaxPassengers(maxPassengers)
    .setAssignedRoute(assignedRoute)
    .build();

        // Insert using DAO
        VehicleDAO dao = new VehicleDAOImpl();
        dao.addVehicle(vehicle);

        // Feedback to user
        response.setContentType("text/html");
        response.getWriter().println("<p>Vehicle added successfully using DAO!</p>");
        response.getWriter().println("<a href='dashboard/managerDashboard.jsp'>Back to Dashboard</a>");
    }
}
