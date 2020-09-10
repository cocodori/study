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
<h1>ADMIN PAGE</h1>

<p>principal : <sec:authentication property="principal"/> </p>
<p>MemberVO : <sec:authentication property="principal.member"/></p>
<p>사용자 이름 : <sec:authentication property="principal.member.username"/></p>
<p>사용자 아이디: <sec:authentication property="principal.member.userid"/></p>
<p>사용자 권한 :<sec:authentication property="principal.member.authList"/></p>
<a href='/logout'><button>로그아웃</button></a>
</body>
</html>