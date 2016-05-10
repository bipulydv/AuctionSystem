<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
response.setDateHeader("Expires", 0);

  // Set standard HTTP/1.1 no-cache headers.

     response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

  // Set IE extended HTTP/1.1 no-cache headers (use addHeader).

     response.addHeader("Cache-Control", "post-check=0, pre-check=0");

  // Set standard HTTP/1.0 no-cache header.

      response.setHeader("Pragma", "no-cache");

  

      if(session.getAttribute("Username")==null)

                response.sendRedirect("Login.jsp");



%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Auctions</title>
<link href="Welcome.css" type="text/css" rel="stylesheet">
</head>
<style>
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
}

li {
    float: left;
}

li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover {
    background-color: #111;
}
</style>
</head>


<body class="user">

<marquee class="title">Welcome to Aricent auctions</marquee>
		<br>
		



<ul>
  <li><a href="MyBidServlet">MyBids</a></li>
  <li><a href="NotificationServlet">Notifications</a></li>
  <li><a href="LogoutServlet">Logout</a></li>
  
</ul>

 <script type="text/javascript">
document.write ('<p> <span id="date-time">', new Date().toLocaleString(), '<\/span>.<\/p>')
if (document.getElementById) onload = function () {
	setInterval ("document.getElementById ('date-time').firstChild.data = new Date().toLocaleString()", 50)
}
</script>

<p class="category"> Please select category</p>
<button class="product" type="button" onclick="location.href='LaptopServlet'">
			<img src="laptop.jpg" style="height: 193px;"></img>
		</button>

<button class="product" type="button" onclick="location.href='MobileServlet'">
			<img src="phone.jpg" style="height: 193px;"></img>
		</button>

</body>
</html>