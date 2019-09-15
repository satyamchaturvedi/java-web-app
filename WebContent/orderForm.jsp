<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/groceryEdit.css">

<title>Order Form</title>

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

  <h1>Order Form</h1>
   <div class="home"><a href ="items.obj">Go Home</a></div>
  <div class="logout">
	<a href="logout.obj">Logout</a>
	</div>
  <form:form action="confirmorder.obj" modelAttribute="order">
  <div class ="tbl"> 
      <table border="1" >
    
         <tr>
            <td>Customer Name</td>
            <td><form:input path="custName" placeholder="Enter Name"/> 
            <form:errors class="err" path="custName" /></td>
            </td>
              
         </tr>
         <tr>
            <td>Customer Mobile</td>
            <td><form:input path="custMobile" placeholder="Enter Mobile Number"/> 
            <form:errors class="err" path="custMobile" /></td>
               </td>
         </tr>
         <tr>
            <td>Item Id</td>
            <td><form:input path="itemId" readonly="true" /> 
               </td>
         </tr>
         <tr>
            <td>Quantity</td>
            <td><form:input path="quantity" readonly="true"/> 
             </td>
         </tr>
         
          <tr>
            <td>Credit Card</td>
            <td><input type="text" name="card" required="required"/> 
             </td>
         </tr>
            <td colspan="2" align="center"><input type="submit" value="Place Order"> </td>
         </tr>
         
   </table>
   </div>
  </form:form>
  <% } %>
</body>
</html>