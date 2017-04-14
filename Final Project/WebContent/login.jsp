<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Log In</title>
	</head>
	<body>
		<%
			Object objUserInDB = request.getAttribute("userInDB");
			String strUserInDB = "";
			if(objUserInDB != null){
				strUserInDB = objUserInDB.toString();
			}
			String message = "";
			if(strUserInDB == "false"){
				message = "Please try logging on again.";
			}
		%>
		<h4 style="color:red;"><%= message %></h4>
		<form action="LogIn" method="post">
			<table cellspacing="2">
				<tr>
					<td colspan="2">Please enter your username and password.</td>
				</tr>
				<tr>
					<td>Username</td>
					<td><input name="username" type="text" size="25" required></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input name="password" type="password" size="25" required></td>
				</tr>
				<tr>
					<td><input type="submit" value="Log In"></td>
				</tr>
			</table>
		</form>

	</body>
</html>