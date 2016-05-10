<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="Login.css" type="text/css" rel="stylesheet">
<title>welcome to auction</title>
</head>


<body class="body" >

<h1 class="heading">WELCOME TO AUCTION</h1>

    <div class="content">
        <div class="form ">
        
         <h1 class = 'Name'>Login</h1>
            <form method="get" action="LoginServlet" class="form">
            <label for="Name1" id="credential">Username</label>
            <input type="text" class="textEntry" name="Username">
            <label for="Password" id="credential" >Password</label>
            <input type="password" class="textEntry" id="Password" name="Password">
            <input type="submit" id="submit" value="login">
            </form>
            <p id="Register"><a href="Register.jsp">New User? Register</a></p>
        </div>
        
    </div>
</body>


</html>