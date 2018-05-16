<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
	<title>Update course</title>
</head>
<body>
	<h1>Update course: ${course.name }</h1>
	
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