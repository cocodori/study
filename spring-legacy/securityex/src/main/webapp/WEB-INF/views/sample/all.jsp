<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>EVERYBODY</h1>

<!-- 모든 사용자가 접근 가능. -->
<sec:authorize access="isAnonymous()">
	<a href="/login">로그인</a>
</sec:authorize>

<!-- 인증된(로그인한) 사용자만 접근 가능 -->
<sec:authorize access="isAuthenticated()">
	<a href="/logout">로그아웃</a>
</sec:authorize>

</body>
</html> 