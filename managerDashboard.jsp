<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="transferObjects.usersDTO" %>
<%@ page session="true" %>

<%
    usersDTO user = (usersDTO) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login/login.jsp");
        return;
    }

    if (!"Manager".equalsIgnoreCase(user.getRole())) {
        response.sendRedirect(request.getContextPath() + "/dashboard/operatorDashboard.jsp");
        return;
    }
%>

<!DOCTYPE html>

<!--
Author Minh Quan Ngo
-->

<html>
<head>
    <meta charset="UTF-8">
    <title>Manager Dashboard</title>
</head>
<body BGCOLOR="#FDF5E6">

<h1 align="center">Manager Dashboard</h1>
<hr width="60%">

<div align="center">
    <p><strong>Welcome, <%= user.getName() %>!</strong></p>

    <table cellpadding="10" cellspacing="10">
        <tr>
            <td></td>
            <td><a href="<%= request.getContextPath() %>/features/addVehicleForm.jsp">Add New Vehicle</a></td>
        </tr>
        <tr>
            <td></td>
            <td><a href="<%= request.getContextPath() %>/GetAllVehiclesServlet">View All Vehicles</a></td>
        </tr>
        <tr>
            <td></td>
            <td><a href="<%= request.getContextPath() %>/ViewGpsReportServlet">View GPS Report</a></td>
        </tr>
        <tr>
            <td>️</td>
            <td><a href="<%= request.getContextPath() %>/maintenanceReport">View Maintenance Alerts</a></td>
        </tr>
        <tr>
            <td>️</td>
            <td><a href="<%= request.getContextPath() %>/features/ScheduleMaintenanceForm.jsp">Schedule Maintenance</a></td>
        </tr>
        <tr>
            <td></td>
            <td><a href="<%= request.getContextPath() %>/fuelReport">View Fuel Usage Report</a></td>
        </tr>
        <tr>
            <td></td>
            <td><a href="<%= request.getContextPath() %>/ViewFuelAlertsServlet">View Fuel Usage Report (with Alerts)</a></td>
        </tr>
        <tr>
            <td></td>
            <td><a href="<%= request.getContextPath() %>/LoadAssignFormServlet">Assign Vehicle</a></td>
        </tr>
         <tr>
            <td> </td>
            <td><a href="<%= request.getContextPath() %>/OperatorPerformanceServlet"> Operator Performance</a></td>
        </tr>
    </table>

    <br><br>

    <form action="<%= request.getContextPath() %>/logout" method="get">
        <button type="submit">🚪 Logout</button>
    </form>
</div>

</body>
</html>
