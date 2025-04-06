<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="transferObjects.OperatorPerformanceDTO" %>

<%
    List<OperatorPerformanceDTO> list = (List<OperatorPerformanceDTO>) request.getAttribute("performanceList");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Operator Performance</title>
</head>
<body BGCOLOR="#FDF5E6">
<center>
    <h2>Operator Performance Report</h2>

    <table border="1" cellpadding="10">
        <tr>
            <th>Operator</th>
            <th>Total Logs</th>
            <th>Out of Service Logs</th>
        </tr>
        <%
            if (list != null && !list.isEmpty()) {
                for (OperatorPerformanceDTO op : list) {
        %>
        <tr>
            <td><%= op.getOperatorEmail() %></td>
            <td><%= op.getTotalLogs() %></td>
            <td><%= op.getOutOfServiceLogs() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="3" style="color:gray;">No data available.</td></tr>
        <%
            }
        %>
    </table>

    <br>
    <a href="<%= request.getContextPath() %>/dashboard/managerDashboard.jsp">⬅ Back to Dashboard</a>
</center>
</body>
</html>
