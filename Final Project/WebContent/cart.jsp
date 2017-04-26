<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="crdelf01.finalproject.DatabaseAccess" %>
<%@ page import="crdelf01.finalproject.UserBean" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>View your cart.</title>
		<link rel="stylesheet" href="final_proj_stylesheet.css">
	</head>
	<body>
		<%
			HttpSession httpSes = request.getSession();
			DatabaseAccess db = (DatabaseAccess) httpSes.getAttribute("dbInstance");
			UserBean user = new UserBean();
			//if the db instance is null have the user re log in
			if(db == null){
				response.sendRedirect("login.jsp");
			}
			else {
				user = db.getUserInfo();	
			}
			
		%>
		<ul style="margin-bottom:15px;">
			<li><a href="home.jsp">Home</a></li>
			<li><a href="cart.jsp">Cart</a></li>
			<li><a href="ViewOrders">Orders</a></li>
			<li><a href="user_info.jsp">Account Info</a>
			<li><a href="LogOut">Log Out</a></li>
		</ul>
		
		<h2>View your cart below.</h2>
		<table cellpadding="3">
			<thead>
				<tr>
					<th align="left">Name</th>
					<th align="left">Price</th>
					<th align="left">Quantity</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sessionScope.shoppingCart}" var="item">
	   				 <tr>
						<td>${item.value.name}</td>
						<td>${item.value.price}</td>
						<td>${item.value.quantity}</td>
						<!-- <td>$${item.value.quantity * item.value.price}</td> -->
	   				 </tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- <input type="button" onclick="window.location.href='checkout.jsp'" value="Checkout" /> -->
		<a href="checkout.jsp">Checkout</a>
	</body>
</html>