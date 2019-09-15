<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/order.css">
<title>Success</title>
</head>
<body>
	<%
		if (session.getAttribute("user") == null) {
	%>
	<h2>You are not logged in.</h2>
	<br>
	<a href="login.obj">Click here to login</a>
	<%
		} else if (session.getAttribute("UId") == null) {
	%>
	<a href="home.obj">Your order has been placed</a>
	<%
		} else {
			session.setAttribute("Uid", null);
	%>

	<h1>Order is placed with id: ${orderid}</h1>
	<a href="logout.obj">Logout</a>
	<br>
	<br>
	<a href="items.obj">Go Home</a>
	<%
		}
	%>
</body>
</html>