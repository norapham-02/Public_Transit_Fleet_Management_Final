<%@ page import="java.util.List" %>
<%@ page import="transferObjects.maintenance_logDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<!--
Author Minh Quan Ngo
-->

<html>
<head>
    <title>Upcoming Maintenance Report</title>
</head>
<body>

    <h1 align="center">🛠️ Upcoming Maintenance Schedule</h1>
    <hr width="60%">

    <div align="center">
        <table border="1" cellpadding="10" cellspacing="0">
            <thead>
                <tr bgcolor="#f2f2f2">
                    <th>Vehicle Number</th>
                    <th>Service Type</th>
                    <th>Service Date</th>
                    <th>Mileage at Service</th>
                    <th>Next Service Due</th>
                </tr>
            </thead>
            <tbody>
            <%
                List<maintenance_logDTO> maintenanceList = (List<maintenance_logDTO>) request.getAttribute("maintenanceList");

                if (maintenanceList != null && !maintenanceList.isEmpty()) {
                    for (maintenance_logDTO log : maintenanceList) {
                        String mileage = (log.getMileageAtService() != null) ? log.getMileageAtService().toString() : "N/A";
            %>
                <tr>
                    <td><%= log.getVehicleNumber() %></td>
                    <td><%= log.getServiceType() %></td>
                    <td><%= log.getServiceDate() %></td>
                    <td><%= mileage %></td>
                    <td><%= log.getNextServiceDue() %></td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr><td colspan="5" align="center">No upcoming maintenance found.</td></tr>
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
