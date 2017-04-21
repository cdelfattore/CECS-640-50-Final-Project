<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="crdelf01.finalproject.DatabaseAccess" %>
<%@ page import="crdelf01.finalproject.ItemBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Item page</title>
		<link rel="stylesheet" href="final_proj_stylesheet.css">
		<style>
			.font_size {
				font-size: 15pt;
			}
		</style>
	</head>
	<body>
		<ul style="margin-bottom:15px;">
			<li><a href="home.jsp">Home</a></li>
			<li><a href="">Cart</a></li>
			<li><a href="">Orders</a></li>
			<li><a href="">Log Out</a></li>
		</ul>
		
		<div class="font_size">${item.name} - $${item.price}</div>
		<div class="font_size">${item.description}</div>
		<div><input style="margin-top:10px;margin-bottom:10px;" type="button" onclick="location.href=''" value="Add to Cart"/></div>
		<div>
			<img src="pic/item_${item.item_id}.jpg">
		</div>
	</body>
</html>