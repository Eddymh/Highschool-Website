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
	<title>Student Homepage</title>
</head>
<body>
	<h1>Welcome Student <c:out value="${currentUser.firstName} ${currentUser.lastName}"></c:out></h1>
	
	<form id="logoutForm" method="POST" action="/logout" >
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="submit" value="Logout" />
	</form>
	
	<h3>Enrolled courses</h3>
	<table>
		<tr>
			<th>Prefix</th>
			<th>Name</th>
			<th>Description</th>
			<th>Capacity</th>
			<th>Teacher</th>
			<th>Teacher's email</th>
			<th>Final Grade</th>
		</tr>
		<c:forEach items="${courses }" var="course">
			<tr>
				<td>${course.prefix }</td>
				<td>${course.name }</td>
				<td>${course.description }</td>
				<td>${course.capacity }</td>
				<td>${course.teacher.firstName } ${course.teacher.lastName }</td>
				<td>${course.teacher.email }</td>
				<td>
					<c:choose>
						<c:when test="${empty course.coursesStudents.get(0).finalGrade }">
							no grade assigned
						</c:when>
						<c:otherwise>
							${course.coursesStudents.get(0).finalGrade}
						</c:otherwise>
					</c:choose>
				</td>
				
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>