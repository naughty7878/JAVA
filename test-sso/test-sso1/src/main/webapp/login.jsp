<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<body>
<h2>Hello World!</h2>
<h2>请登录1</h2>
	<form action="http://check.x.com:8080/test-sso-auth/login" method="POST">
		<span>用户名：</span><input type="text" name="username" />
		<span>密码：</span><input type="password" name="password" />
		<input type="hidden" value="${redirectUrl }" name="redirectUrl"/>
		<input type="submit"/>
	</form>
</body>
</html>
