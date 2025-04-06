<%@ page import="java.util.List" %>
<%@ page import="transferObjects.usersDTO" %>
<%@ page import="transferObjects.vehiclesDTO" %>

<h2>Assign Vehicle to Operator</h2>

<form method="post" action="AssignVehicleServlet">
    <label>Choose Operator:</label>
    <select name="operatorEmail" required>
        <% 
            List<usersDTO> operators = (List<usersDTO>) request.getAttribute("operators");
            if (operators != null && !operators.isEmpty()) {
                for (usersDTO operator : operators) {
        %>
        <option value="<%= operator.getEmail() %>">
            <%= operator.getName() %> - <%= operator.getEmail() %>
        </option>
        <% 
                }
            } else {
        %>
        <option disabled selected>No approved operators available</option>
        <% } %>
    </select>
    <br><br>

    <label>Choose Vehicle:</label>
    <select name="vehicleId" required>
        <% 
            List<vehiclesDTO> vehicles = (List<vehiclesDTO>) request.getAttribute("vehicles");
            if (vehicles != null && !vehicles.isEmpty()) {
                for (vehiclesDTO v : vehicles) {
        %>
        <option value="<%= v.getId() %>">
            <%= v.getVehicleNumber() %> - <%= v.getVehicleType() %>
        </option>
        <% 
                }
            } else {
        %>
        <option disabled selected>No vehicles available</option>
        <% } %>
    </select>
    <br><br>

    <button type="submit" <% if (operators == null || operators.isEmpty() || vehicles == null || vehicles.isEmpty()) { %> disabled <% } %>>
        Assign Vehicle
    </button>
</form>
