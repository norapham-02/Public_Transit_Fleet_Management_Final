<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>

<!--
Author Minh Quan Ngo
-->

<html>
<head>
    <title>Register</title>
</head>
<body bgcolor="#FDF5E6">

<h1 align="center">User Registration</h1>
<hr width="60%">

<div align="center">
    <%-- Show error if present --%>
    <% if (request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
    <% } %>

    <form action="<%= request.getContextPath() %>/register" method="post">
        <table cellpadding="10">
            <tr>
                <td>Full Name:</td>
                <td><input type="text" name="name" required></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="email" name="email" required></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" required></td>
            </tr>
            <tr>
                <td> Role:</td>
                <td>
                    <select name="role" required>
                        <option value="Manager">Manager</option>
                        <option value="Operator">Operator</option>
                    </select>
                </td>
            </tr>
        </table>

        <br>
        <button type="submit"> Register</button>
    </form>

    <br>
    <a href="<%= request.getContextPath() %>/login"> Already have an account? Login</a>
</div>

</body>
</html>
