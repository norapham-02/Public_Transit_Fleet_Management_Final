<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<h2 style="color: red;"> Fuel Usage Alerts</h2>

<table border="1">
    <tr>
        <th>Vehicle</th>
        <th>Type</th>
        <th>Date</th>
        <th>Fuel Consumed (L)</th>
        <th>Mileage</th>
    </tr>
<%
        List<transferObjects.fuel_usageDTO> alerts = (List<transferObjects.fuel_usageDTO>) request.getAttribute("alerts");
    for (transferObjects.fuel_usageDTO log : alerts) {
%>
    <tr>
        <td><%= log.getVehicleNumber() %></td>
        <td><%= log.getVehicleType() %></td>
        <td><%= log.getDate() %></td>
        <td style="color: red;"><%= log.getFuelConsumed() %></td>
        <td><%= log.getMileage() %></td>
    </tr>
<%
    }
%>
</table>
