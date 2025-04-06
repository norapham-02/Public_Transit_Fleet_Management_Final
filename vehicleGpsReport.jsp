<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="transferObjects.gps_trackingDTO" %>
<%@ page session="true" %>

<%
    List<gps_trackingDTO> logs = (List<gps_trackingDTO>) request.getAttribute("gpsLogs");
%>

<!DOCTYPE html>

<!--
Author Minh Quan Ngo
-->

<html>
<head>
    <meta charset="UTF-8">
    <title>Vehicle GPS Report</title>
</head>
<body BGCOLOR="#FDF5E6">

    <h1 align="center">📍 Vehicle GPS Tracking Report</h1>
    <hr width="60%">

    <div align="center">
        <table border="1" cellpadding="10" cellspacing="0">
            <thead>
                <tr bgcolor="#ffe4b5">
                    <th>ID</th>
                    <th>Vehicle Number</th>
                    <th>Station Name</th>
                    <th>Status</th>
                    <th>Arrival Time</th>
                    <th>Departure Time</th>
                    <th>Logged By</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (logs != null && !logs.isEmpty()) {
                        for (gps_trackingDTO log : logs) {
                %>
                <tr>
                    <td><%= log.getId() %></td>
                    <td><%= log.getVehicleNumber() %></td>
                    <td><%= log.getStationName() %></td>
                    <td><%= log.getStatus() %></td>
                    <td><%= log.getArrivalTime() != null ? log.getArrivalTime() : "N/A" %></td>
                    <td><%= log.getDepartureTime() != null ? log.getDepartureTime() : "N/A" %></td>
                    <td><%= log.getLoggedBy() %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="7" align="center" style="color:gray;">No GPS logs available</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <br><br>
        <a href="<%= request.getContextPath() %>/dashboard/managerDashboard.jsp">⬅ Back to Dashboard</a>
    </div>

</body>
</html>
