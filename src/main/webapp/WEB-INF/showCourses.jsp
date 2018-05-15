<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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