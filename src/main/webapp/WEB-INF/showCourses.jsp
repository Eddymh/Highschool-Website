<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html >
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
	<title>Add courses</title>
</head>
<body>
	<a href="/admin"> HomePage </a>
	<h1>Courses available</h1>
	<h2>Student: ${student }</h2>
	<strong>${error }</strong>
	<table>
		<tr>
			<th>Prefix</th>
			<th>Name</th>
			<th>Description</th>
			<th>Capacity</th>
			<th>Teacher</th>
			<th>Availability</th>
		</tr>
			<c:forEach items="${allCourses }" var="course">
				<tr>
					<td>${course.prefix }</td>
					<td>${course.name }</td>
					<td>${course.description }</td>
					<td>${fn:length(course.coursesStudents) }/${course.capacity }</td>
					<td>${course.teacher.firstName } ${course.teacher.lastName }</td>
					<td>
						<form action="/admin/students" method="POST">
							<input type=hidden name="studentId" value="${studentId }" />
							<input type=hidden name="courseId" value="${course.id }" />
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<input type="submit" value="Add">
						</form>
					</td>
				</tr>
			</c:forEach>
	</table>
</body>
</html>