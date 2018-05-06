<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Courses available</h1>
	<table>
		<tr>
			<th>Prefix</th>
			<th>Name</th>
			<th>Description</th>
			<th>Capacity</th>
			<th>Teacher</th>
			<th>Availability</th>
		</tr>
			<c:forEach items="${courses }" var="course">
				<tr>
					<td>${course.prefix }</td>
					<td>${course.name }</td>
					<td>${course.description }</td>
					<td>${course.capacity }</td>
					<td>${course.teacher.firstName } ${course.teacher.lastName }</td>
					<td>
						<form action="/student/courses/${course.id }" method="POST">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<input type="submit" value="Add">
						</form>
					</td>
				</tr>
			</c:forEach>
	</table>
</body>
</html>