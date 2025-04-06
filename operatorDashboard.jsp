<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="transferObjects.usersDTO" %>
<%@ page session="true" %>

<%
    usersDTO user = (usersDTO) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login/login.jsp");
        return;
    }

    if (!"Operator".equalsIgnoreCase(user.getRole())) {
        response.sendRedirect(request.getContextPath() + "/dashboard/managerDashboard.jsp");
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
    <title>Operator Dashboard</title>
</head>
<body BGCOLOR="#FDF5E6">

<h1 align="center"> Operator Dashboard</h1>
<hr width="60%">

<div align="center">
    <p><strong>Welcome, <%= user.getName() %>!</strong></p>

    <table cellpadding="10" cellspacing="10">
        <tr>
            <td></td>
            <td><a href="<%= request.getContextPath() %>/features/GpsLogForm.jsp">
                 Log Arrival / Departure / Out of Service
            </a></td>
        </tr>
        <tr>
            <td>️</td>
            <td><a href="<%= request.getContextPath() %>/ViewGpsReportServlet">
                 View My GPS Logs
            </a></td>
        </tr>
        <tr>
            <td>️</td>
            <td><a href="<%= request.getContextPath() %>/ViewAssignedVehicle">
                 View Assigned Vehicle/Route
            </a></td>
        </tr>
    </table>

    <br><br>

    <form action="<%= request.getContextPath() %>/logout" method="get">
        <button type="submit">🚪 Logout</button>
    </form>
</div>

</body>
</html>
