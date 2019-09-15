<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/register.css">

<title>SignUp</title>
</head>
<body>
<div class="register">
	<h1>SignUp Form</h1>

	<form:form action="confirmsignup.obj" modelAttribute="user">
		<div class="user">
		<i class="fa fa-user icon"></i>
		<label for='usrname'></label>
				<form:input path="name" placeholder="Enter Name" required="required"/>
				 <form:errors class="err" path="name" />
				</div>
 <div class="email">
  <i class="fa fa-envelope icon"></i>
				<form:input path="userName" value="" placeholder="Enter UserName" required="required"/> 
				<form:errors class="err" path="userName" />
				</div>
<div class="pass">
  	<i class="fa fa-key icon"></i>
  	<label for='psw'></label>
			
				<form:input type="password" path="password" placeholder="Enter Password" required="required"/> 
				<form:errors class="err" path="password" />
				</div>
			
			<input type="submit" value="Sign Up">
			
			
	</form:form>
</div>
</body>
</html>