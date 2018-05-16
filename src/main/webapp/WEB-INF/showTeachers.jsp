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
	<title>Teachers</title>
</head>
<body>
	
	<a href="/admin"> HomePage </a>
	<h1>Registered Teachers</h1>
	<table>
		<tr>
			<th>First name</th>
			<th>Last name</th>
			<th>Username</th>
			<th>email</th>
			<th>Street</th>
			<th>City</th>
			<th>Zip code</th>
			<th>Phone number</th>
			<th>Courses assigned</th>
		</tr>
		<c:forEach items="${allTeachers }" var="teacher">
			<tr>
				<td>${teacher.firstName }</td>
				<td>${teacher.lastName }</td>
				<td>${teacher.username }</td>
				<td>${teacher.email }</td>
				<td>${teacher.street }</td>
				<td>${teacher.city }</td>
				<td>${teacher.zipCode }</td>
				<td>${teacher.phoneNumber }</td>
				<td>
					<c:forEach items="${teacher.courses }" var="course" >
						<p>${course.prefix }-${course.name }</p>
					</c:forEach>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>