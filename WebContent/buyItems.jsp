<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/groceryEdit.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Grocery List</title>
</head>
<body>
<%
	if(session.getAttribute("user")==null){
%>
	<h2>You are not logged in.</h2><br>
	<a href="login.obj">Click here to login</a>
<% }else {%>
  <c:choose>
     <c:when test="${slist.size()>0}">
      <h2><center>Online Shopping Mart</center></h2>
      <h3> </h3>
 <%--  <h3><center>Available Items</center></h3> --%> <div class="logout">
	<a href="logout.obj">Logout</a>
	
	</div>
   <div class="tbl">
  <table border="1" align="center" class="table"> 
 
   <tr>
      <td><b>Grocery Id</b></td>
      <td><b>Name</b></td>
      <td><b>Price(Rs)</b></td>
      <td><b>Category</b></td>
      <td><b>Quantity</b></td>
    
      <td colspan="2"><b>Action</b></td>
   </tr> 
     <c:forEach var="gitem" items="${slist}">
    <tr>
   <td>${gitem.id}</td> 
   <td>${gitem.name}</td>
   <td>${gitem.price}</td>
   <td>${gitem.category}</td>
   <td>${gitem.quantity}</td>
  <td><a href="buyItem.obj?itemid=${gitem.id}"><h5>Buy Item</h5></a>
   <!-- <td><a href="editGrocery.obj?grocid=${gitem.id}">Edit Grocery</a></td> -->
   <!-- <td><a href="deleteGrocery.obj?grocid=${gitem.id}" onclick="return check()">Delete Item</a></td> -->
</tr>
</c:forEach> 
</div>
  </table>
 
     </c:when>
     <c:otherwise>
       <h3><center>No Data Found</center></h3>
     </c:otherwise>
  </c:choose>
  <br>
 
  <% } %>
</body>
</html>