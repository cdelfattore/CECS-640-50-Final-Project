<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="crdelf01.finalproject.OrderLineBean" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Order Id: ${sessionScope.order.order_id}</title>
		<link rel="stylesheet" href="final_proj_stylesheet.css">
		<style>
			.header {
				font-size: 14pt;
    			font-weight: bold;
			}
		</style>		
	</head>
	<body>
		<ul style="margin-bottom:15px;">
			<li><a href="home.jsp">Home</a></li>
			<li><a href="cart.jsp">Cart</a></li>
			<li><a href="ViewOrders">Orders</a></li>
			<li><a href="user_info.jsp">Account Info</a>
			<li><a href="LogOut">Log Out</a></li>
		</ul>
		
		<div class="header">Order Information</div>
		<div>Order Number: ${sessionScope.order.order_id}</div>
		<div>Order Total: $${sessionScope.order.total}</div>
		
		<div></div><br/>
		
		<div class="header">Items in this Order</div>
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
				<form method="post" action="UpdateOrder">
					<c:forEach items="${sessionScope.orderlines}" var="ol">
		   				 <tr>
							<td>${ol.itemName}</td>
							<td>${ol.itemPrice}</td>
							<td><input name="${ol.order_line_id}_qty" type="number" value="${ol.quantity}" style="width:40px;"/></td>
							<td>${ol.total}</td>
		   				 </tr>
					</c:forEach>
					<input type="submit" value="Update Order" />
				</form>
			</tbody>
		</table>
	</body>
</html>