<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!--
Author Minh Quan Ngo
-->

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
</head>
<body bgcolor="#FDF5E6">

<h1 align="center">Public Transit Login</h1>
<hr width="60%">

<div align="center">
    <%-- Show error if present --%>
    <% if (request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
    <% } %>

    <form action="<%= request.getContextPath() %>/login" method="post">
        <table cellpadding="8">
            <tr>
                <td>Username:</td>
                <td><input type="text" name="username" required></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" required></td>
            </tr>
            <tr>
                <td> Role:</td>
                <td>
                    <select name="role">
                        <option value="manager">Manager</option>
                        <option value="operator">Operator</option>
                    </select>
                </td>
            </tr>
        </table>

        <br>
        <button type="submit" name="action" value="login">➡ Login</button>
    </form>

    <br><br>

    <form action="<%= request.getContextPath() %>/register" method="get">
        <button type="submit"> Register</button>
    </form>
</div>

</body>
</html>
