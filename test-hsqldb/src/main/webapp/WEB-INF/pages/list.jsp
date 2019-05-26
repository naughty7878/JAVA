<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	员工列表
	<table>
		<tr>
			<th>id</th>
			<th>lastName</th>
			<th>gender</th>
			<th>email</th>
		</tr>
		<c:forEach items="${allEmps }" var="emp">
			<tr>
				<td>${emp.id }</td>
				<td>${emp.lastName }</td>
				<td>${emp.gender }</td>
				<td>${emp.email}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>