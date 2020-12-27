<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form acton='result.jsp'>
		ID : <input type='text' name='id'> <br>
		PW : <input type='password' name='pwd'>
	</form>
	
	<a href='<%=request.getContextPath() %>/elTest/memberForm.jsp'>회원가입</a>
</body>
</html>