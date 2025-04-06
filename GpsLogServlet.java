package viewlayer;

import dataAccessLayer.GpsTrackingDAO;
import dataAccessLayer.GpsTrackingDAOImpl;
import transferObjects.gps_trackingDTO;
import transferObjects.usersDTO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 *
 * @author MinhQuanNgo
 */
public class GpsLogServlet extends HttpServlet {

    private final GpsTrackingDAO dao = new GpsTrackingDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String vehicleNumber = request.getParameter("vehicleNumber");
        String station = request.getParameter("stationName");
        String status = request.getParameter("status");

        String arrival = request.getParameter("arrivalTime");
        String departure = request.getParameter("departureTime");

        HttpSession session = request.getSession();
        usersDTO user = (usersDTO) session.getAttribute("user");

        gps_trackingDTO log = new gps_trackingDTO();
        log.setVehicleNumber(vehicleNumber);
        log.setStationName(station);
        log.setStatus(status);
        log.setLoggedBy(user.getEmail());

        if (arrival != null && !arrival.isEmpty())
            log.setArrivalTime(LocalDateTime.parse(arrival));
        if (departure != null && !departure.isEmpty())
            log.setDepartureTime(LocalDateTime.parse(departure));

        dao.insert(log);
        response.sendRedirect("dashboard/operatorDashboard.jsp");
    }
}
