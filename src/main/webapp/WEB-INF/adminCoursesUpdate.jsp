<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Update ${course.name }</h1>
	
	<strong>${errors }</strong>
	
	<form action="/admin/courses/${course.id }" method="POST" >
		<label>Name:
			<input type="text" value="${course.name }" name="name" >
		</label><br>
		<label>Prefix:
			<input type="text" value="${course.prefix }" name="prefix" >
		</label><br>
		<label>Description:
			<input type="text" value="${course.description }" name="description" >
		</label><br>
		<label>Capacity:
			<input type="number" value="${course.capacity }" name="capacity" >
		</label><br>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="submit" value="update">
	</form>
	
</body>
</html>