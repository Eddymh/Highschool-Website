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
	<title>Students</title>
</head>
<body>

	<a href="/admin"> HomePage </a>
	<h1>Registered Students</h1>
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
			<th>Courses taken</th>
			<th>Assign a course</th>
		</tr>
		<c:forEach items="${allStudents }" var="student">
			<tr>
				<td>${student.firstName }</td>
				<td>${student.lastName }</td>
				<td>${student.username }</td>
				<td>${student.email }</td>
				<td>${student.street }</td>
				<td>${student.city }</td>
				<td>${student.zipCode }</td>
				<td>${student.phoneNumber }</td>
				<td>
					<c:forEach items="${student.coursesStudents }" var="cs">
						<span>${cs.course.prefix }-${cs.course.name } <br /> </span>
					</c:forEach>
				</td>
				<td><a href="students/${student.id }">See courses available</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>