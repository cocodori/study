<!-- 스크립트릿을 이용한 로그인 예제 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("password");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	if(id == null || id.length() == 0) {
%>
	<h1>아이디를 입력하세요</h1>
	<a href='/login.html'>다시 로그인 하기</a>	
<%	
	} else {
		if(id.equals("admin")) {
%> 
	<h1>관리자로 로그인하셨습니다.</h1>
	<button>회원 관리</button>
<% 	
		} else { 
%>
	<h1>환영합니다. <%=id %>님</h1>
<%
			}
	}
%>	
	


</body>
</html>