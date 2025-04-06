package viewlayer;

import dataAccessLayer.GpsTrackingDAO;
import dataAccessLayer.GpsTrackingDAOImpl;
import transferObjects.OperatorPerformanceDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class OperatorPerformanceServlet extends HttpServlet {
    private final GpsTrackingDAO dao = new GpsTrackingDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<OperatorPerformanceDTO> performanceList = dao.getOperatorPerformance();
        request.setAttribute("performanceList", performanceList);
        request.getRequestDispatcher("features/operatorPerformance.jsp").forward(request, response);
    }
}
