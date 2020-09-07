<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body align='center'>
	<h1>로그인</h1>
	<h3>${error }</h3>
	<h3>${logout }</h3>
	
	<form action="/login" method="post">
		<div>
			<input type='text' name='username' value='admin'>
		</div>
		<div>
			<input type='password' name='password' value='admin'>
		</div>
		<div>
			<input type='submit'>
		</div>
		<input type='hidden' name='${_csrf.parameterName }' value='${_csrf.token }'>
	</form>
</body>
</html>