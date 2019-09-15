<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Success</title>
</head>
<body>
	<h1>${msg}</h1>
	<%
		if (session.getAttribute("user") == null) {
	%>
	<h2>You are not logged in.</h2>
	<br>
	<a href="login.obj">Click here to login</a>
	<%
		} else {
	%>
	<c:choose>
		<c:when test="${slist.size()>0}">
			<h2>
				<center>Online Shopping Mart</center>
			</h2>
			<h3>
				<center>Available Items</center>
			</h3>
			<!-- <div class="logout">
				<a href="logout.obj">Logout</a>
			</div> -->
			<div class="tbl">
				<table border="2" align="center">

					<tr>
						<td><b>Grocery Id</b></td>
						<td><b>Name</b></td>
						<td><b>Price(Rs)</b></td>
						<td><b>Category</b></td>
						<td><b>Quantity</b></td>
					</tr>
					<c:forEach var="gitem" items="${slist}">
						<tr>
							<td>${gitem.id}</td>
							<td>${gitem.name}</td>
							<td>${gitem.price}</td>
							<td>${gitem.category}</td>
							<td>${gitem.quantity}</td>
						</tr>
					</c:forEach>
					</div>
				</table>
		</c:when>
		<c:otherwise>
			<h3>
				<center>No Data Found</center>
			</h3>
		</c:otherwise>
	</c:choose>
	<div class="logout">
		<a href="logout.obj">Logout</a> <br>
		<a href="adminHome.obj">Go Home</a>
	</div>


	<%
		}
	%>
</body>
</html>