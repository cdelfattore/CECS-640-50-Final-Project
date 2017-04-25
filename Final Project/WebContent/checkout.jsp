<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Checkout</title>
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
		
		<h2>Review your order below</h2>
		<table cellpadding="3">
			<thead>
				<tr>
					<th align="left">Name</th>
					<th align="left">Price</th>
					<th align="left">Quantity</th>
					<th align="left">Total</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sessionScope.shoppingCart}" var="item">
	   				 <tr>
						<td>${item.value.name}</td>
						<td>${item.value.price}</td>
						<td>${item.value.quantity}</td>
						<td>$${item.value.quantity * item.value.price}</td>
	   				 </tr>
				</c:forEach>
				<tr>
					<td></td><td></td><td>Grand Total:</td><td>$${sessionScope.grandTotal}</td>
				</tr>
			</tbody>
		</table>
		<form action="CreateOrder" method="post">
			<input type="submit" value="Place Order" />
		</form>

	</body>
</html>