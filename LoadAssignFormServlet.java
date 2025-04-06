package viewlayer;

import dataAccessLayer.UserDAO;
import dataAccessLayer.UserDAOImpl;
import dataAccessLayer.VehicleDAO;
import dataAccessLayer.VehicleDAOImpl;
import transferObjects.usersDTO;
import transferObjects.vehiclesDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class LoadAssignFormServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAOImpl();
    private final VehicleDAO vehicleDAO = new VehicleDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get only approved operators
        List<usersDTO> operators = userDAO.getUsersByRole("Operator");

        // Get all vehicles
        List<vehiclesDTO> vehicles = vehicleDAO.getAllVehicles();

        // Set attributes for the JSP page
        request.setAttribute("operators", operators);
        request.setAttribute("vehicles", vehicles);

        // Forward to the assignment form
        RequestDispatcher dispatcher = request.getRequestDispatcher("/features/assignVehicle.jsp");
        dispatcher.forward(request, response);
    }
}
