<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<body>
<h2>统一登录界面</h2>
	<form action="login" method="POST">
		<span>用户名：</span><input type="text" name="username" />
		<span>密码：</span><input type="password" name="password" />
		<input type="hidden" value="${param.redirectUrl }" name="redirectUrl"/>
		<input type="submit"/>
	</form>
</body>
</html>
