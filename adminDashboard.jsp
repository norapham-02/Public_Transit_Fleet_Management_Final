<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!--
Author Minh Quan Ngo
-->

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Dashboard</title>
</head>
<body BGCOLOR="#FDF5E6">

<h1 align="center"> Admin Dashboard</h1>
<hr width="60%">

<div align="center">
    <p><strong>Welcome, Admin!</strong></p>
    <p>Use the options below to manage system access:</p>

    <table cellpadding="10" cellspacing="10">
        <tr>
            <td></td>
            <td>
                <form action="<%= request.getContextPath() %>/pendingUsers.jsp" method="get">
                    <button type="submit"> View & Approve Pending Users</button>
                </form>
            </td>
        </tr>
    </table>

    <br><br>
    <form action="<%= request.getContextPath() %>/login" method="get">
        <button type="submit"> Logout</button>
    </form>
</div>

</body>
</html>
