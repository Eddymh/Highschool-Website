<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<a href="/admin"> HomePage </a>
	<h1>User Registration</h1>
	<strong>${error }</strong>
	
	<form:form method="post" action="/admin/user-registration" modelAttribute="user">
	
		<label for="role">Role:
			<select name="role">
				<option value="notset" selected>----Select----</option>
				<option value="student" >Student</option>
				<option value="teacher">Teacher</option>
				<option value="admin">Admin</option>
			</select><br>
		</label>
		<form:label path="firstName">First Name:
			<form:errors path="firstName"></form:errors>
			<form:input path="firstName" type="text" />
		</form:label><br>
		<form:label path="lastName">Last Name: 
			<form:errors path="lastName"></form:errors>
			<form:input path="lastName" type="text" />
		</form:label><br>
		<form:label path="username">Username: 
			<form:errors path="username"></form:errors>
			<form:input path="username" type="text" />
		</form:label><br>
		<form:label path="email">Email: 
			<form:errors path="email"></form:errors>
			<form:input path="email" type="text" />
		</form:label><br>
		<form:label path="street">Street: 
			<form:errors path="street"></form:errors>
			<form:input path="street" type="text" />
		</form:label><br>
		<form:label path="city">City: 
			<form:errors path="city"></form:errors>
			<form:input path="city" type="text" />
		</form:label><br>
		<form:label path="zipCode">Zip code: 
			<form:errors path="zipCode"></form:errors>
			<form:input path="zipCode" type="text" />
		</form:label><br>
		<form:label path="phoneNumber">Phone Number: 
			<form:errors path="phoneNumber"></form:errors>
			<form:input path="phoneNumber" type="text" />
		</form:label><br>
		<form:label path="password">Password: 
			<form:errors path="password"></form:errors>
			<form:input path="password" type="password" />
		</form:label><br>
		<form:label path="confirm">Confirm password: 
			<form:errors path="confirm"></form:errors>
			<form:input path="confirm" type="password" />
		</form:label><br>
				
		<input type="submit" value="Register" />
	</form:form>
</body>
</html>