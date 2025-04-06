package viewlayer;

import observer.FuelAlertStorage;
import transferObjects.fuel_usageDTO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ViewFuelAlertsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<fuel_usageDTO> alerts = FuelAlertStorage.getInstance().getAlerts();
        request.setAttribute("alerts", alerts);
        request.getRequestDispatcher("/features/fuelAlerts.jsp").forward(request, response);
    }
}
