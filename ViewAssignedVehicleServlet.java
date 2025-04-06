package viewlayer;

import dataAccessLayer.VehicleDAO;
import dataAccessLayer.VehicleDAOImpl;
import transferObjects.usersDTO;
import transferObjects.vehiclesDTO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class ViewAssignedVehicleServlet extends HttpServlet {

    private final VehicleDAO dao = new VehicleDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        usersDTO user = (usersDTO) session.getAttribute("user");

        if (user == null || !"Operator".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/login/login.jsp");
            return;
        }

        vehiclesDTO vehicle = dao.findVehicleByOperator(user.getEmail());

        request.setAttribute("vehicle", vehicle);
        request.getRequestDispatcher("/features/viewAssignedVehicle.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Displays the vehicle assigned to the logged-in operator.";
    }
}
