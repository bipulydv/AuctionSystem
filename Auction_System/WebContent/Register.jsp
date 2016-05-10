<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="Login.css" type="text/css" rel="stylesheet">
<title>welcome to auction</title>
</head>
<body class="body">

<h1 class="heading">USER REGISTRATION FORM</h1>

    <div class="content">
        <div class="form ">
        
         
            <form method="get" action="RegisterServlet" class="form">
            <label for="Name1" id="credential">Employee ID</label>
            <input type="text" class="textEntry" name="Username">
            <label for="Password" id="credential" >Password</label>
            <input type="password" class="textEntry" id="Password" name="Password">
            <label for="Password" id="credential" >Confirm Password</label>
            <input type="password" class="textEntry" id="Password" name="ConfirmPassword">
            <input type="submit" id="submit" value="Register">
            </form>
        </div>
        
    </div>
    
</body>
</html>