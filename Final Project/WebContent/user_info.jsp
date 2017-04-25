<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="crdelf01.finalproject.DatabaseAccess" %>
<%@ page import="crdelf01.finalproject.UserBean" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>View User Account info</title>
		<link rel="stylesheet" href="final_proj_stylesheet.css">
	</head> 
	<body>
		<ul style="margin-bottom:15px;">
			<li><a href="home.jsp">Home</a></li>
			<li><a href="cart.jsp">Cart</a></li>
			<li><a href="ViewOrders">Orders</a></li>
			<li><a href="user_info.jsp">Account Info</a>
			<li><a href="LogOut">Log Out</a></li>
		</ul>
		<%
			HttpSession httpSes = request.getSession();
			DatabaseAccess db = (DatabaseAccess) httpSes.getAttribute("dbInstance");
			UserBean user = db.getUserInfo();
		%>
	
		<h3>User Information</h3>
		<div>First Name: <%= user.getFirst() != null ? user.getFirst() : "" %></div>
		<div>Last Name: <%= user.getLast() != null ? user.getLast() : "" %></div>
		<div>Street: <%= user.getStreet() != null ? user.getStreet() : "" %></div>
		<div>City: <%= user.getCity() != null ? user.getCity() : "" %></div>
		<div>State: <%= user.getState() != null ? user.getState() : "" %></div>
		<div>Zip: <%= user.getZip() != null ? user.getZip() : "" %></div>
		<div>Email: <%= user.getEmail() != null ? user.getEmail() : "" %></div>
		<div>Phone: <%= user.getPhone() != null ? user.getPhone() : "" %></div>
	</body>
</html>