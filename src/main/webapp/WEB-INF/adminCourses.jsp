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
	<title>Create, Update, Delete</title>
</head>
<body>
	<form id="logoutForm" method="POST" action="/logout" >
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<div class = "form-group">
			<button type="submit" class="btn btn-primary btn-secondary " >Logout</button>
		</div>
	</form>
	
	<a href="/admin"> HomePage </a>
	
	<h1>Courses</h1>
	
	<strong>${errors }</strong>
	
	<table class="table table-hover">
		<tr>
			<th>Prefix</th>
			<th>Name</th>
			<th>Description</th>
			<th>Teacher</th>
			<th>Student count</th>
			<th>update</th>
			<th>Delete</th>
		</tr>
		<c:forEach var="course" items="${allCourses}">
			<tr>
				<td>${course.prefix }</td>
				<td>${course.name }</td>
				<td><textarea readonly class="form-control" rows="4" cols="40">${course.description }</textarea></td>
				<td>${course.teacher.firstName } ${course.teacher.lastName }</td>
				<td>${fn:length(course.coursesStudents)}/${course.capacity }</td>
				<td><button type="button" class="btn btn-warning" ><a href="/admin/courses/${course.id }">Update</a></button></td>
				<td>
					<form action="/admin/courses/delete/${course.id }" method="post">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<button type="submit" class="btn btn-danger" >Delete</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<H1>Create new course</H1>
	
	<form:form action="/admin/courses" method="POST" modelAttribute="course">
		<div class="form-group row">
			<form:label path="prefix" for="inputPrefix" class="col-sm-2 col-form-label">Prefix</form:label>
			<form:errors path="prefix" />
			<div class="col-sm-10">
				<form:input path="Prefix" id="inputPrefix" placeholder="Prefix"/>
			</div>
		</div>
		<div class="form-group row">
			<form:label path="name" for="inputName" class="col-sm-2 col-form-label" >Name</form:label>
			<form:errors path="name" />
			<div class="col-sm-10">
				<form:input path="name" id="inputName" placeholder="Name"/>
			</div>
		</div>
		<div class="form-group row">
			<form:label path="capacity" for="inputCapacity" class="col-sm-2 col-form-label" >Capacity</form:label>
			<form:errors path="capacity" />
			<div class="col-sm-10">
				<form:input path="capacity" type="number" id="inputCapacity" placeholder="Capacity"/>
			</div>
		</div>
		<div class="form-group row">
			<form:label path="description" for="inputDescription" class="col-sm-2 col-form-label" >Description</form:label>
			<form:errors path="description" />
			<div class="col-sm-10">
				<form:textarea rows="4" cols="40" path="description" id="inputDescription" placeholder="Description" />
			</div>
		</div>
		<div class="form-group row">
			<form:label path="teacher" class="col-sm-2 col-form-label" >Teacher</form:label>
				<form:select path="teacher" >
					<c:forEach var="teacher" items="${allTeachers}" >
						<div class="col-sm-10">
							<form:option value="${teacher }">${teacher.firstName} ${teacher.lastName}</form:option>
						</div>
					</c:forEach>
				</form:select>
		</div>
		<div class="col-auto my-1">
			<button type="submit" class="btn btn-primary">Create</button>
		</div>
	</form:form>
	
</body>
</html>