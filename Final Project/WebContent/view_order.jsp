<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="crdelf01.finalproject.OrderLineBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Order Id: ${order.order_id}</title>
		<link rel="stylesheet" href="final_proj_stylesheet.css">		
	</head>
	<body>
		<ul style="margin-bottom:15px;">
			<li><a href="home.jsp">Home</a></li>
			<li><a href="cart.jsp">Cart</a></li>
			<li><a href="">Orders</a></li>
			<li><a href="">Log Out</a></li>
		</ul>
		
		<h3>Order Information</h3>
		<div>Order Number: ${order.order_id}</div>
		<div>Order Total: $${order.total}</div>
		
		<div></div><br/>
		
		<div>Items in this Order</div>
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
				<c:forEach items="${orderlines}" var="ol">
	   				 <tr>
						<td>${ol.itemName}</td>
						<td>${ol.itemPrice}</td>
						<td>${ol.quantity}</td>
						<td>${ol.total}</td>
	   				 </tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>