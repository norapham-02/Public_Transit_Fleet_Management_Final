<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<!--
Author Minh Quan Ngo
-->

<html>
<head>
    <title>Add New Vehicle</title>
</head>
<body>

    <h1 align="center"> Add New Vehicle</h1>
    <hr width="60%">

    <form action="<%= request.getContextPath() %>/AddVehicleServlet" method="post" align="center">
        <fieldset style="display:inline-block; text-align:left; padding: 20px; border: 2px solid #000;">
            <legend><strong>Vehicle Information</strong></legend>

            <table>
                <tr>
                    <td><label for="vehicleType">Vehicle Type:</label></td>
                    <td><input type="text" id="vehicleType" name="vehicleType" required></td>
                </tr>
                <tr>
                    <td><label for="vehicleNumber">Vehicle Number:</label></td>
                    <td><input type="text" id="vehicleNumber" name="vehicleNumber" required></td>
                </tr>
                <tr>
                    <td><label for="fuelType">Fuel Type:</label></td>
                    <td><input type="text" id="fuelType" name="fuelType" required></td>
                </tr>
                <tr>
                    <td><label for="consumptionRate">Consumption Rate (L/100km):</label></td>
                    <td><input type="number" step="0.01" id="consumptionRate" name="consumptionRate" required></td>
                </tr>
                <tr>
                    <td><label for="maxPassengers">Max Passengers:</label></td>
                    <td><input type="number" id="maxPassengers" name="maxPassengers" required></td>
                </tr>
                <tr>
                    <td><label for="assignedRoute">Assigned Route (Route ID):</label></td>
                    <td><input type="number" id="assignedRoute" name="assignedRoute" required></td>
                </tr>
            </table>

            <br>
            <div align="center">
                <button type="submit">✅ Add Vehicle</button>
            </div>
        </fieldset>
    </form>

    <br><br>
    <div align="center">
        <a href="<%= request.getContextPath() %>/dashboard/managerDashboard.jsp">← Back to Dashboard</a>
    </div>

</body>
</html>
