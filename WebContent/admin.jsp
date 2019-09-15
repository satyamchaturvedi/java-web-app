<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/admin.css">
<title>Admin</title>
</head>
<body>
	<%
		if (session.getAttribute("user") == null) {
	%>
	<h2>You are not logged in.</h2>
	<br>
	<a href="login.obj">Click here to login</a>
	<%
		} else {
	%>
	<h1><center>Welcome Admin</center></h1>
<div class="logout">
				<a href="logout.obj">Logout</a>
			</div>
	<form:form action="createadmin.obj" modelAttribute="admin">
		<div class="tbl">
			<table border="2" align="center">

				<tr>
					<td>Admin Name</td>
					<td><form:input path="userName" placeholder="Enter AdminName" />
						<form:errors class="err" path="userName" /></td>
					</td>

				</tr>
				<tr>
					<td>Name</td>
					<td><form:input path="name" placeholder="Enter Name" /> <form:errors
							class="err" path="name" /></td>
					</td>
				</tr>
				<tr>
					<td>Password</td>
					<td><form:input path="password" placeholder="Enter password" />
						<form:errors class="err" path="password" /></td>
					</td>
				</tr>
				<td colspan="2" align="center"><input type="submit"
					value="add admin"></td>
				</tr>

			</table>
		</div>
	</form:form>
	<br>
	<br>
	<c:choose>
		<c:when test="${slist.size()>0}">
			<h2>
				<center>Online Shopping Mart</center>
			</h2>
			<h3>
				<center>Available Items</center>
			</h3>
			
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
	<br>

	<form:form action="addItem.obj" modelAttribute="item">
		<div class="tbl">
			<table  border="2" align="center">


				<tr>
					<td>Name</td>
					<td><form:input path="name" placeholder="Enter Name" /> <form:errors
							class="err" path="name" /></td>
					</td>
				</tr>
				<tr>
					<td>Price</td>
					<td><form:input path="price" placeholder="Enter Price" /></td>
				</tr>

				<tr>
					<td>Category</td>
					<td><form:input path="category" placeholder="Enter Category" />
					</td>
				</tr>

				<tr>
					<td>Quantity</td>
					<td><form:input path="quantity" placeholder="Enter quantity" />

					</td>
				</tr>
				<td colspan="2" align="center"><input type="submit"
					value="add item"></td>
				</tr>

			</table>
		</div>
	</form:form>
<br>
<br>


	<form:form action="updateItem.obj" modelAttribute="item">
		<div class="tbl">
			<table border="2" align="center">

				<tr>
					<td>Item Id</td>
					<td><form:input path="id" placeholder="Enter ItemId" /></td>
				</tr>
				<tr>
					<td>Quantity</td>
					<td><form:input path="quantity" placeholder="Enter quantity" />

					</td>
				</tr>
				<td colspan="2" align="center"><input type="submit"
					value="Update item"></td>
				</tr>

			</table>
		</div>
	</form:form>

<br>
<br>
<%-- <form:form action="deleteItem.obj" modelAttribute="item">
		<div class="tbl">
			<table border="1">

				<tr>
					<td>Item Id</td>
					<td><form:input path="id" placeholder="Enter ItemId" /></td>
				</tr>
				<td colspan="2" align="center"><input type="submit"
					value="delete Item"></td>
				</tr>

			</table>
		</div>
	</form:form>
 --%>


	<%
		}
	%>
</body>
</html>