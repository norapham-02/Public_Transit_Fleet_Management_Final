package viewlayer;

import businesslayer.VehicleBusinessLogic;
import transferObjects.vehiclesDTO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author phaml
 */
public class GetAllVehiclesServlet extends HttpServlet {
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Vehicles Summary View</title>");
            out.println("</head>");
            out.println("<body bgcolor='#FDF5E6'>");

            out.println("<h1 align='center'>🚍 Vehicles Summary View</h1>");
            out.println("<hr width='60%'>");

            VehicleBusinessLogic logic = new VehicleBusinessLogic();
            List<vehiclesDTO> vehicles = logic.getAllVehicles();

            out.println("<div align='center'>");

            out.println("<p><b>Total vehicles loaded:</b> " + (vehicles == null ? "null" : vehicles.size()) + "</p>");

            if (vehicles != null && !vehicles.isEmpty()) {
                out.println("<table border='1' cellpadding='8' cellspacing='0'>");
                out.println("<tr bgcolor='#ffe4b5'>");
                out.println("<th>ID</th>");
                out.println("<th>Type</th>");
                out.println("<th>Number</th>");
                out.println("<th>Fuel Type</th>");
                out.println("<th>Consumption Rate</th>");
                out.println("<th>Max Passengers</th>");
                out.println("<th>Assigned Route</th>");
                out.println("<th>Last Service Date</th>");
                out.println("</tr>");

                for (vehiclesDTO v : vehicles) {
                    out.printf("<tr>"
                            + "<td>%d</td>"
                            + "<td>%s</td>"
                            + "<td>%s</td>"
                            + "<td>%s</td>"
                            + "<td>%.2f</td>"
                            + "<td>%d</td>"
                            + "<td>%d</td>"
                            + "<td>%s</td>"
                            + "</tr>",
                            v.getId(),
                            v.getVehicleType(),
                            v.getVehicleNumber(),
                            v.getFuelType(),
                            v.getConsumptionRate(),
                            v.getMaxPassengers(),
                            v.getAssignedRoute(),
                            (v.getLastServiceDate() != null ? v.getLastServiceDate().toString() : "N/A"));
                }

                out.println("</table>");
            } else {
                out.println("<p style='color:gray;'>No vehicles found in the system.</p>");
            }

            out.println("<br><a href='" + request.getContextPath() + "/dashboard/managerDashboard.jsp'>⬅ Back to Dashboard</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            out.println("<p style='color:red;'>❌ Error fetching vehicle data: " + e.getMessage() + "</p>");
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
        return "Displays all vehicles in an HTML table.";
    }
}
    