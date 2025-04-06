package viewlayer;

import command.MaintenanceCommand;
import command.ScheduleMaintenanceCommand;
import dataAccessLayer.Maintenance_logDAO;
import dataAccessLayer.Maintenance_logDAOImpl;
import transferObjects.maintenance_logDTO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class ScheduleMaintenanceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        String serviceDate = request.getParameter("serviceDate");
        String serviceType = request.getParameter("serviceType");
        double mileage = Double.parseDouble(request.getParameter("mileage"));
        String nextServiceDue = request.getParameter("nextServiceDue");

        maintenance_logDTO log = new maintenance_logDTO();
        log.setVehicleId(vehicleId);
        log.setServiceDate(serviceDate);
        log.setServiceType(serviceType);
        log.setMileageAtService(mileage);
        log.setNextServiceDue(nextServiceDue);

        Maintenance_logDAO dao = new Maintenance_logDAOImpl();
        MaintenanceCommand command = new ScheduleMaintenanceCommand(dao, log); 
        command.execute();


        response.sendRedirect(request.getContextPath() + "/maintenanceReport");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Optional: redirect to form
        request.getRequestDispatcher("/features/ScheduleMaintenanceForm.jsp").forward(request, response);
    }
}
