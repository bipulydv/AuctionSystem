<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="java.sql.ResultSet"%>
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
<h1 class="heading">Current Auction</h1>
<%ResultSet rst=(ResultSet)request.getAttribute("resultset"); %>

<table style="width:100%">
  <tr>
    <th>Product ID</th>
    <th>Product Name</th>		
    <th>Description</th>
    <th>Initial Bid</th>
    <th>Category</th>
    <th>Status</th>
  </tr>
  <% while(rst.next()){%>
	  <tr>
    <td><%out.println(rst.getString("product_id")); %></td>
    <td><%out.println(rst.getString("product_name")); %></td>		
    <td><%out.println(rst.getString("description")); %></td>
    <td><%out.println(rst.getString("initial_bid")); %></td>
    <td><%out.println(rst.getString("category")); %></td>
    <td><%out.println(rst.getString("status")); %></td>
  </tr>
  <tr>
  <% } %>
  
</table>

</body>
</html>