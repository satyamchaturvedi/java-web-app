<%@ page language="java" contentType="text/html; charset=UTF-8" errorPage="error.jsp" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/groceryEdit.css">
<title>Edit Form</title>

<style>
  .err{
    color:red; 
  }
</style>

</head>
<body>
<%
if(session.getAttribute("user")==null){
%>
	<h2>You are not logged in.</h2><br>
	<a href="login.obj">Click here to login</a>
<% }else {%>

  <h1>Shopping Cart</h1>
  <h2>Enter the quantity</h2>
  <div class="home"><a href ="items.obj">Go Home</a></div>
  <div class="logout">
	<a href="logout.obj">Logout</a>
	</div>
  <form:form action="confirmBuyItem.obj" modelAttribute="item">
	<div class ="tbl">     
 	<table border="1" >
         <tr>
            <td>Item ID</td>
            <td><form:input path="id" readonly="true" /> 
            </td>
         </tr>
         <tr>
            <td>Item Name</td>
            <td><form:input path="name" readonly="true"  /> 
            </td>
         </tr>
         <tr>
            <td>Price(Rs)</td>
            <td><form:input path="price" readonly="true" /> 
               </td>
         </tr>
         <tr>
            <td>Category</td>
            <td><form:input path="category" readonly="true" /> 
               </td>
         </tr>
         <tr>
            <td>Available Quantity</td>
            <td><form:input path="quantity" readonly="true"/> 
             </td>
         </tr>
         <tr>
            <td>Units Required</td>
            <td><input type="number" name="req" value="" min="1" max="${quantity}" required/> 
             <form:errors class="err" path="name" /></td>
            </td>
         </tr>
         <tr>
            <td colspan="2" align="center"><input type="submit" value="Buy Item"> </td>
         </tr>
         
   </table>
</div>

  </form:form>

  <% } %>
</body>
</html>