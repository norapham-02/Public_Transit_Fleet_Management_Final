<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Schedule Maintenance</title>
</head>
<body bgcolor="#FDF5E6">
<center>
    <h2>📅 Schedule Maintenance</h2>
    <form action="<%= request.getContextPath() %>/ScheduleMaintenanceServlet" method="post">
        <label>Vehicle ID:</label>
        <input type="number" name="vehicleId" required><br><br>

        <label>Service Date:</label>
        <input type="date" name="serviceDate" required><br><br>

        <label>Service Type:</label>
        <input type="text" name="serviceType" required><br><br>

        <label>Mileage at Service:</label>
        <input type="number" step="0.1" name="mileage" required><br><br>

        <label>Next Service Due:</label>
        <input type="date" name="nextServiceDue" required><br><br>

        <button type="submit">Schedule</button>
    </form>
    <br><a href="<%= request.getContextPath() %>/dashboard/managerDashboard.jsp">⬅ Back to Dashboard</a>
</center>
</body>
</html>
