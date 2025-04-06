/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewlayer;
import dataAccessLayer.FuelUsageDAO;
import strategy.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import transferObjects.fuel_usageDTO;
/**
 *
 * @author phaml
 */

public class FuelReportServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        try {
            FuelUsageDAO dao = new FuelUsageDAO();
            List<fuel_usageDTO> usageList = dao.getAllFuelUsage();
            for (fuel_usageDTO entry : usageList) {
    EfficiencyStrategy strategy;
    String fuelType = entry.getFuelType();

    if ("diesel".equalsIgnoreCase(fuelType)) {
        strategy = new DieselStrategy();
    } else if ("electric".equalsIgnoreCase(fuelType)) {
        strategy = new ElectricStrategy();
    } else {
        strategy = new CNGStrategy();
    }

    double efficiency = strategy.calculate(entry.getFuelConsumed(), entry.getMileage());
    entry.setEfficiency(efficiency);
}

            request.setAttribute("fuelUsageList", usageList);
            request.getRequestDispatcher("/features/fuelReport.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

