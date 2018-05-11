<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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