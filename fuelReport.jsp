<%@ page import="java.util.List" %>
<%@ page import="transferObjects.fuel_usageDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<!--
Author Minh Quan Ngo
-->

<html>
<head>
    <title>Fuel Usage Report</title>
</head>
<body>

    <h1 align="center"> Fuel Usage Report</h1>
    <hr width="60%">

    <div align="center">
        <table border="1" cellpadding="10" cellspacing="0">
            <thead>
                <tr bgcolor="#f2f2f2">
                    <th>Vehicle #</th>
                    <th>Vehicle Type</th>
                    <th>Fuel Consumed (L)</th>
                    <th>Mileage (km)</th>
                    <th>Date</th>
                    <th>Efficiency (km/L)</th>
                </tr>
            </thead>
            <tbody>
            <%
                List<fuel_usageDTO> fuelUsageList = (List<fuel_usageDTO>) request.getAttribute("fuelUsageList");

                if (fuelUsageList != null && !fuelUsageList.isEmpty()) {
                    for (fuel_usageDTO f : fuelUsageList) {
            %>
                <tr>
                    <td><%= f.getVehicleNumber() %></td>
                    <td><%= f.getVehicleType() %></td>
                    <td><%= f.getFuelConsumed() %></td>
                    <td><%= f.getMileage() %></td>
                    <td><%= f.getDate() %></td>
                    <td><%= (f.getEfficiency() == 0 ? "N/A" : String.format("%.2f", f.getEfficiency())) %></td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr><td colspan="6" align="center">No data available</td></tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>

    <br><br>
    <div align="center">
        <a href="<%= request.getContextPath() %>/dashboard/managerDashboard.jsp">← Back to Dashboard</a>
    </div>

</body>
</html>
