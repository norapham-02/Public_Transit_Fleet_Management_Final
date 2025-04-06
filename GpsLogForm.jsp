<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="transferObjects.usersDTO" %>
<%@ page session="true" %>

<%
    usersDTO user = (usersDTO) session.getAttribute("user");
    if (user == null || !"Operator".equalsIgnoreCase(user.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login/login.jsp");
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
    <title>Log GPS Entry</title>
</head>
<body BGCOLOR="#FDF5E6">

<h1 align="center"> Vehicle Out-of-Service Log</h1>
<hr width="60%">

<div align="center">
    <form method="post" action="<%= request.getContextPath() %>/GpsLogServlet">
        <fieldset style="display: inline-block; padding: 20px; border: 2px solid #333;">
            <legend><b>Log Details</b></legend>
            <table>
                <tr>
                    <td><label for="vehicleNumber">Vehicle Number:</label></td>
                    <td><input type="text" id="vehicleNumber" name="vehicleNumber" required></td>
                </tr>
                <tr>
                    <td><label for="stationName">Station Name:</label></td>
                    <td><input type="text" id="stationName" name="stationName" required></td>
                </tr>
                <tr>
                    <td> <label for="status">Status:</label></td>
                    <td>
                        <select name="status" id="status" required>
                            <option value="OUT_OF_SERVICE">Out of Service</option>
                            <option value="ACTIVE">Back to Service</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="arrivalTime">Break Start (Arrival):</label></td>
                    <td><input type="datetime-local" id="arrivalTime" name="arrivalTime"></td>
                </tr>
                <tr>
                    <td><label for="departureTime">Break End (Departure):</label></td>
                    <td><input type="datetime-local" id="departureTime" name="departureTime"></td>
                </tr>
                <tr>
                    <td><label for="fuelUsed">Fuel Used (L):</label></td>
                    <td><input type="number" id="fuelUsed" name="fuelUsed" step="0.1" required></td>
                </tr>

            </table>
            <br>
            <div align="center">
                <button type="submit"> Submit Log</button>
            </div>
        </fieldset>
    </form>

    <br><br>
    <a href="<%= request.getContextPath() %>/dashboard/operatorDashboard.jsp">⬅ Back to Dashboard</a>
</div>

<script>
    document.getElementById("fuelUsed").addEventListener("input", function () {
        if (parseFloat(this.value) > 50) {
            alert("High fuel usage detected!");
        }
    });
</script>


</body>
</html>
