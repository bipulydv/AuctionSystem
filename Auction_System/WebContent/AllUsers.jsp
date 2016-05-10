<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="Welcome.css" type="text/css" rel="stylesheet">
<title>Current Biddings</title>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
}
</style>
</head>
<body>
<h1 class="heading">All Users</h1>
<%ResultSet rst=(ResultSet)request.getAttribute("resultset"); %>

<table style="width:100%">
  <tr>
    <th>Employee ID</th>
    <th>Employee Name</th>		
    <th>Email Address</th>
    <th>Department</th>
  </tr>
  <% while(rst.next()){%>
	  <tr>
    <td><%out.println(rst.getString("employee_id")); %></td>
    <td><%out.println(rst.getString("employee_name")); %></td>		
    <td><%out.println(rst.getString("email_address")); %></td>
    <td><%out.println(rst.getString("department")); %></td>
  </tr>
  <tr>
  <% } %>
  
</table>

</body>
</html>