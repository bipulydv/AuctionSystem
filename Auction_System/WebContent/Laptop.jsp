<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*" %>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Timestamp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="refresh" content="60">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Laptops</title>
 <link href="Details.css" type="text/css" rel="stylesheet">
 	
	<script language="javascript" type="text/javascript">

</script>
</head>

<body class="laptop_background">
<p class="details">Different laptops available for auction</p>

<p class="back_button">
	<button  type="button" onclick="location.href='UserWelcome.jsp'">Back</button></p>
	<% ResultSet rs=(ResultSet)request.getAttribute("resultSet"); 

int i=1; 
while (rs.next()) {
	%>
	<p class="details">
	<%
 int pid=rs.getInt("product_id");
	
	out.println(pid);%>
	<br><% 
	//String name=
	out.println(rs.getString("product_name"));%>
	<br><% 
	out.println(rs.getString("description"));%>
	<br><% 
	out.println(rs.getFloat("initial_bid"));%>
			<br><% 						
			out.println(rs.getString("status"));%>
			<br>

			<br><%
			

        String len1 = rs.getString("image");

        int len = len1.length();

        byte [] b = new byte[len];

        InputStream in = rs.getBinaryStream("image");

        int index = in.read(b, 0, len);

        String str="D:\\gur44297\\AuctionSystem\\AuctionSystem\\WebContent\\laptopDetails"+i+".jpg";

        OutputStream outImej = new FileOutputStream(str);

        while (index != -1)

        {

        outImej.write(b, 0, index);

        index = in.read(b, 0, len);

        

        }

        outImej.close();
 out.println("<img src='laptopDetails"+i+".jpg'  style='width:200px;height:180px;'>");
   %><br>
   <% 
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
   
	<form action="BiddingLaptopServlet" method="get">
	<input name="p_id"  value="<%= rs.getInt("product_id") %>" type="hidden"/>
	<input name="i_value"  value="<%= i %>" type="hidden"/>
	<input type="submit" value="want to bid?"/>
	</form>
</p>
<br>
<br>

<% i++;} %>



</body>
</html>
