<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
	<title>Admin Homepage</title>
</head>
<body>
	<form id="logoutForm" method="POST" action="/logout" >
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<div class = "form-group">
			<button type="submit" class="btn btn-primary btn-secondary " >Logout</button>
		</div>
	</form>
	<h1>ADMIN PANEL</h1>
	<h1>Welcome ${currentUser}</h1>
	
	<strong>${success }</strong>
	
	
	<p></p>
	<nav class="nav flex-column">
		<a class="nav-link" href="/admin/courses">Courses</a>
		<a class="nav-link" href="/admin/user-registration">Register a user</a>
		<a class="nav-link" href="/admin/students">Students</a>
		<a class="nav-link" href="/admin/teachers">Teachers</a>
	</nav>
</body>
</html>