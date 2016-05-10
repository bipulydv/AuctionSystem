

<%@page import="java.sql.Timestamp"%>
<%@page import="java.io.*"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <link href="Details.css" type="text/css" rel="stylesheet">


</head>
<body class="mobile_background">
<p class="back_button">
	<button  type="button" onclick="location.href='UserWelcome.jsp'">Back</button></p>
<% ResultSet rs=(ResultSet)request.getAttribute("bid_product"); 

while (rs.next()) {
 String i=(String)session.getAttribute("im_value");
	%>
	<p class="details">
	<% 
	out.println(rs.getString("product_name"));%>
	<br><% 
	out.println(rs.getString("description"));%>
	<br><% 
	out.println(rs.getFloat("initial_bid"));%>
			<br><% 						
			out.println(rs.getString("status"));
			%><br><%

                        Timestamp t=rs.getTimestamp("end_time");

                         java.util.Date date= new java.util.Date();

                        Timestamp t1=new Timestamp(date.getTime());

                    
                        Long diff=t.getTime()-t1.getTime();
						
                        
                        // long diffSeconds = diff / 1000 % 60;
                         long Minutes = diff / (60 * 1000) % 60;
                         long Hours = diff / (60 * 60 * 1000) % 24;
                         long Days = diff / (24 * 60 * 60 * 1000);

                         out.println("Time left: " + Days+"  Days "+Hours+  "  hours  " +Minutes+"  minutes " );
                        
                        
                        %>
			
			<br>
<%
out.println("<img src='MobileDetails"+i+".jpg'  style='width:200px;height:180px;'>");
%>
			<br>
			</p>
			<%
	RequestDispatcher req=request.getRequestDispatcher("MaxBidServlet");
	req.include(request,response);%>
	<p class="details">
	
<% 

	

Double d=(Double)request.getAttribute("price");
session.setAttribute("price",d);

if(d==0.0)
	out.println("be the first one to bid....!!");
	else
		out.println("product maximum price is"+request.getAttribute("price"));
	response.setIntHeader("Refresh", 20);
	%>
	</p>
	<form action="InsertBidServlet" method="get">
	<input name="bid"    type="text"/>
	<input type="submit" value="Bid now"/>
	</form>
	
	<%
			
	
}
			%>
	

</body>
</html>`