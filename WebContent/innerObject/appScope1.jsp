<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.setAttribute("name", "김코코");
	application.setAttribute("address", "1호선");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 스코프 테스트</title>
</head>
<body>
	<h1>'name' is session scope.</h1>
	<h1>'address' is application scope.</h1>
	<h1><a href='/innerObject/appScope2.jsp'>result</a></h1>
</body>
</html>