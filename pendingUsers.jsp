<%@ page import="java.sql.*, dataAccessLayer.DataSource" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<!--
Author Minh Quan Ngo
-->

<html>
<head>
    <title>Pending Users</title>
</head>
<body bgcolor="#FDF5E6">

<h1 align="center">Pending User Approvals</h1>
<hr width="60%">

<div align="center">
    <%
        try (Connection conn = DataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT user_id, name, email, role FROM users WHERE is_approved = FALSE");

            if (!rs.isBeforeFirst()) {
    %>
        <p><i>No pending users at the moment.</i></p>
    <%
            } else {
    %>
        <table border="1" cellpadding="10" cellspacing="0">
            <thead>
                <tr bgcolor="#ffe4b5">
                    <th>Name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <%
                while (rs.next()) {
            %>
                <tr>
                    <td><%= rs.getString("name") %></td>
                    <td><%= rs.getString("email") %></td>
                    <td><%= rs.getString("role") %></td>
                    <td>
                        <a href="<%= request.getContextPath() %>/approveUser?id=<%= rs.getInt("user_id") %>">Approve</a>
                    </td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    <%
            }
        } catch (Exception e) {
            out.println("<p style='color:red;'> Error loading pending users: " + e.getMessage() + "</p>");
        }
    %>

    <br><br>
    <a href="<%= request.getContextPath() %>/admin">⬅ Back to Admin Dashboard</a>
</div>

</body>
</html>
