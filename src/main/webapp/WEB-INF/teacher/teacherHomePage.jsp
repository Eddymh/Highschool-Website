<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html >
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
	<title>Teacher homepage</title>
</head>
<body>
	<h1>Welcome Teacher <c:out value="${currentUser.firstName} ${currentUser.lastName}"></c:out></h1>
	
	<form id="logoutForm" method="POST" action="/logout" >
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="submit" value="Logout" />
	</form>
	
	<h3>Courses assigned</h3>
	
	<table>
		<tr>
			<th>Prefix</th>
			<th>Name</th>
			<th>Description</th>
			<th>Enrolled/Capacity</th>
			<th>Students list</th>
		</tr>
			<c:forEach items="${courses }" var="course">
				<tr>
					<td>${course.prefix }</td>
					<td>${course.name }</td>
					<td>${course.description }</td>
					<td>${fn:length(course.coursesStudents)}/${course.capacity}</td>
					<td><a href="/teacher/homepage/${course.id }">See Students</a></td>
				</tr>
			</c:forEach>
	</table>
	
</body>
</html>