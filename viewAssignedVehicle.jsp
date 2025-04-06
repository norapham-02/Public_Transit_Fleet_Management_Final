<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="transferObjects.vehiclesDTO" %>
<%
    vehiclesDTO vehicle = (vehiclesDTO) request.getAttribute("vehicle");
%>

<!DOCTYPE html>
<html>
<head><title>Assigned Vehicle</title></head>
<body BGCOLOR="#FDF5E6">
<center>
    <h2>Your Assigned Vehicle</h2>

    <% if (vehicle != null) { %>
        <p><strong>Vehicle Number:</strong> <%= vehicle.getVehicleNumber() %></p>
        <p><strong>Assigned Route:</strong> <%= vehicle.getAssignedRoute() %></p>
    <% } else { %>
        <p style="color:red;">No assigned vehicle found.</p>
    <% } %>

    <a href="<%= request.getContextPath() %>/dashboard/operatorDashboard.jsp">⬅ Back to Dashboard</a>
</center>
</body>
</html>
