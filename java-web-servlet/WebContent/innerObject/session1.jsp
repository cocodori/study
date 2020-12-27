<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String name = (String)session.getAttribute("name");
	session.setAttribute("address", "안드로메다");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장객체테스트</title>
</head>
<body>
이름은 <%=name %>입니다.
<a href='/innerObject/session2.jsp'> 세 번째 페이지로 이동 </a>
</body>
</html>