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
	<h1>Courses</h1>
	
	<table>
		<tr>
			<th>Prefix</th>
			<th>Name</th>
			<th>Capacity</th>
			<th>Description</th>
			<th>Teacher</th>
			<th>Student Count</th>
			<th>Modify</th>
		</tr>
		<c:forEach var="course" items="${allCourses}">
			<tr>
				<td>${course.prefix }</td>
				<td>${course.name }</td>
				<td>${course.capacity }</td>
				<td>${course.description }</td>
				<td><p>TBA</p></td>
				<td><p>TBA</p></td>
				<td><p>TBA</p></td>
			</tr>
		</c:forEach>
	</table>
	
	<form:form action="/admin/courses" method="POST" modelAttribute="course">
		<form:label path="prefix" >Prefix:
			<form:errors path="prefix" />
			<form:input path="Prefix" />
		</form:label><br>
		<form:label path="name" >Name:
			<form:errors path="name" />
			<form:input path="name" />
		</form:label><br>
		<form:label path="capacity" >Capacity:
			<form:errors path="capacity" />
			<form:input path="capacity" type="number"/>
		</form:label><br>
		<form:label path="description" >Description:
			<form:errors path="description" />
			<form:input path="description" />
		</form:label><br>
		<input type="submit" value="create" >
	</form:form>
	
</body>
</html>