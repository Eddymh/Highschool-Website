<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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