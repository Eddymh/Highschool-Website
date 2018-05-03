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
	<h1>Registration</h1>
	<form:form method="post" action="/student/registration" modelAttribute="user">
	
		<form:label path="firstName">First Name:
			<form:errors path="firstName"></form:errors>
			<form:input path="firstName" type="text" />
		</form:label><br>
		<form:label path="lastName">Last Name: 
			<form:errors path="lastName"></form:errors>
			<form:input path="lastName" type="text" />
		</form:label><br>
		<form:label path="email">Email: 
			<form:errors path="email"></form:errors>
			<form:input path="email" type="text" />
		</form:label><br>
		<form:label path="username">Username	: 
			<form:errors path="username"></form:errors>
			<form:input path="username" type="text" />
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