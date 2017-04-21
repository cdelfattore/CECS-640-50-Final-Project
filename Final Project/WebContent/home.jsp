<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Welcome</title>
		<link rel="stylesheet" href="final_proj_stylesheet.css">
	</head>
	<body>
		<ul style="margin-bottom:15px;">
			<li><a href="home.jsp">Home</a></li>
			<li><a href="">Cart</a></li>
			<li><a href="">Orders</a></li>
			<li><a href="">Log Out</a></li>
		</ul>

		<div>Welcome to the shopping site!</div>
				
		<form method="post" action="ItemServlet">
			<table>
				<tr>
					<td>Search for an item below.</td>
				</tr>
				<tr>
					<td><input name="searchTerm" type="text" size="50" required></td>
					<td><input type="submit" value="Search"></td>
				</tr>
			</table>
		</form>
		
		<table cellpadding="3">
			<thead>
				<tr>
					<th align="left">Name</th>
					<th align="left">Description</th>
					<th align="left">Price</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${itemList}" var="item">
	   				 <tr>
	   				 	<td>${item.name}</td>
	   				 	<td>${item.description}</td>
	   				 	<td>${item.price}</td>
	   				 	<td><a href="ViewItem?id=${item.item_id}">View Item</a>
	   				 </tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>