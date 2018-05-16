<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
	<title>Login Page</title>
</head>
<body>
	<c:if test="${logoutMessage != null}" >
		<c:out value="${logoutMessage}"></c:out>
	</c:if>

	<h1>Login</h1>
	
	<c:if test="${errorMessage != null}">
		<c:out value="${errorMessage}"></c:out>
	</c:if>
	
	<form method="POST" action="/login">
		<label for="username">Username</label>
			<input type="text" id="username" name="username" />
		<label for="password">Password</label>
			<input type="password" id="password" name="password" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="submit" value="Login" />
	</form>

</body>
</html>