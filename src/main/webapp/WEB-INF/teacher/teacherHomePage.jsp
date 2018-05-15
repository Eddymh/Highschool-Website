<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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