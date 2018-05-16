<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
	<title>Assign Grade</title>
</head>
<body>
	<h1>${course.prefix } - ${course.name } </h1>
	<table>
	<tr>
		<th>First name</th>
		<th>Last name</th>
		<th>Username</th>
		<th>email</th>
		<th>Assigned Final Grade</th>
		<th>Final Grade</th>
	</tr>
		<c:forEach items="${students }" var="student">
			<tr>
				<td>${student.firstName }</td>
				<td>${student.lastName }</td>
				<td>${student.username }</td>
				<td>${student.email }</td>
				<td>
					<c:choose>
						<c:when test="${empty student.coursesStudents.get(0).finalGrade }">
							no grade assigned
						</c:when>
						<c:otherwise>
							${course.coursesStudents.get(0).finalGrade}
						</c:otherwise>
					</c:choose>
				</td>
  				<td>
					<form method="post" action="/teacher/assignGrade" >
						<input type="number" name="finalGrade" />
						<input type="hidden" name="courseId" value="${course.id }" />
						<input type="hidden" name="studentId" value="${student.id }" />
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<input type="submit" value="assignGrade" />
					</form>
				</td>

			</tr>
		</c:forEach>
	</table>
</body>
</html>