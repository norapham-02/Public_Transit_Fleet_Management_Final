package viewlayer;

import dataAccessLayer.VehicleDAO;
import dataAccessLayer.VehicleDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class AssignVehicleServlet extends HttpServlet {

    private final VehicleDAO vehicleDAO = new VehicleDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        String operatorEmail = request.getParameter("operatorEmail");

        vehicleDAO.assignVehicleToOperator(vehicleId, operatorEmail);

        response.sendRedirect(request.getContextPath() + "/dashboard/managerDashboard.jsp");
    }
}
