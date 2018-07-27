<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Course: ${course.name}</h1>
	<table>
		<tr>
			<th>Student name</th>
			<th>Actions</th>
		</tr>
		
		<c:forEach var="student" items="${students}">
			<tr>
				<td>${student.firstName } ${student.lastName }</td>
				<td><a href="/admin/courses/drop/${course.id}/${student.id}">drop</a></td>
			</tr>
		</c:forEach>
		
	</table>
</body>
</html>