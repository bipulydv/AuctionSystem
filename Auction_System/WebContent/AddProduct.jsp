<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%// Set to expire far in the past.

response.setDateHeader("Expires", 0);

// Set standard HTTP/1.1 no-cache headers.

response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

// Set IE extended HTTP/1.1 no-cache headers (use addHeader).

response.addHeader("Cache-Control", "post-check=0, pre-check=0");

// Set standard HTTP/1.0 no-cache header.

response.setHeader("Pragma", "no-cache");

 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="Welcome.css" type="text/css" rel="stylesheet">
<title>Add Product</title>

<style>
input[type=text], select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=submit] {
    width: 100%;
    background-color: black;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type=submit]:hover {
    background-color: black;
}

div {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 40px;
}
</style>
<body >

<h3 class="heading">ADD PRODUCT</h3>

<div>
  <form method="get" action="AddProductServlet">
  
    <label for="product id">Product ID</label>
    <input type="text" id="fname" name="product_id">

    <label for="product name">Product Name</label>
    <input type="text" id="lname" name="product_name">

    <label for="product description">Product Description</label>
    <input type="text" id="lname" name="product_description">
    
    <label for="product min_bid">Min_bid</label>
    <input type="text" id="lname" name="product_min_bid">
    
    <label for="product category">product category</label>
    <select id="country" name="product_category">
      <option value="laptop">Laptop</option>
      <option value="mobile">Mobile</option>
    </select>
  
   <label for="product min_bid">Min_bid</label>
    <input type="text" id="lname" name="product_min_bid">
  
  	<label for="product image">Image</label>
    <input type="file" id="lname" name="product_image">
    <input type="submit" value="Submit">
  </form>
</div>

</body>


</head>

</html>