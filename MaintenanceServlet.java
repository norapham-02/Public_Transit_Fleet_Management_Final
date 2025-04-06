package viewlayer;

import dataAccessLayer.Maintenance_logDAO;
import dataAccessLayer.Maintenance_logDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferObjects.maintenance_logDTO;


public class MaintenanceServlet extends HttpServlet {
    private Maintenance_logDAO dao = new Maintenance_logDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        maintenance_logDTO log = new maintenance_logDTO();
        log.setVehicleId(Integer.parseInt(request.getParameter("vehicleId")));
        log.setServiceDate(request.getParameter("serviceDate"));
        log.setServiceType(request.getParameter("serviceType"));
        log.setMileageAtService(Double.valueOf(request.getParameter("mileageAtService")));
        log.setNextServiceDue(request.getParameter("nextServiceDue"));

        dao.addMaintenanceLog(log);
        response.sendRedirect("maintenanceList.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<maintenance_logDTO> list = dao.getUpcomingMaintenance();
        request.setAttribute("maintenanceList", list);
        request.getRequestDispatcher("maintenanceList.jsp").forward(request, response);
    }
}
