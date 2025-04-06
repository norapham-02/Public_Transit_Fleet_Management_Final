

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<!--
Author Minh Quan Ngo
-->

<html>
<head>
    <title>Registration Submitted</title>
</head>
<body bgcolor="#FDF5E6">

    <h1 align="center"> Registration Submitted</h1>
    <hr width="60%">

    <div align="center">
        <p>Thank you for registering, <strong><%= request.getAttribute("name") %></strong>!</p>
        <p>Your account is currently <b>pending approval</b>.</p>
        <p>Please wait for an admin to approve your account before logging in.</p>

        <br><br>
        <a href="<%= request.getContextPath() %>/login"> Return to Login</a>
    </div>

</body>
</html>
