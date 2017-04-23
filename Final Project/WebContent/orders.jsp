<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="crdelf01.finalproject.OrderBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title>Your Orders</title>
			<link rel="stylesheet" href="final_proj_stylesheet.css">
		</head> 
	<body>
		<ul style="margin-bottom:15px;">
			<li><a href="home.jsp">Home</a></li>
			<li><a href="cart.jsp">Cart</a></li>
			<li><a href="ViewOrders">Orders</a></li>
			<li><a href="">Log Out</a></li>
		</ul>
		
		<h3>Your past orders are listed below</h3>
		<table cellpadding="3">
			<thead>
				<tr>
					<th align="left">Order Id</th>
					<th align="left">Total</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sessionScope.orderlist}" var="order">
	   				 <tr>
						<td>${order.order_id}</td>
						<td>${order.total}</td>
						<td><a href="ViewOrder?id=${order.order_id}">View or Modify Order</a></td>
						<td><a href="">Cancel Order</a></td>
	   				 </tr>
				</c:forEach>
			</tbody>
		</table>

	</body>
</html>