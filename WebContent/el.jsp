<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<jsp:useBean id="m1" class='pro13.sec01.MemberBean' />
<jsp:setProperty name='m1' property='name' value='콩콩'/>

<jsp:useBean id="m2" class='java.util.ArrayList'/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>empty연산자 테스트</h1>
	<h2 style='color:red;'>null이면 true</h1>
	<h2 style='color:blue;'>null이 아니면 false</h1>
	<h3><p>\${empty m1} : ${empty m1 }</p></h3>
	<h3><p>\${not empty m1} : ${not empty m1 }</p></h3>

	<h3><p>\${empty m2} : ${empty m2 }</p></h3>
	<h3><p>\${not empty m2} : ${not empty m2 }</p></h3>
</body>
</html>