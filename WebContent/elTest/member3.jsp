<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
%>

	<jsp:useBean id="m" class='pro13.sec01.MemberBean' />
	<jsp:setProperty name='m' property='*'/>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border='1' align='center'>
		<tr align='center' bgcolor='#99ccff'>
			<td width='20%'><b>아이디</b></td>
			<td width='20%'><b>이름</b></td>
			<td width='20%'><b>이메일</b></td>
		</tr>
		<tr align='center'>
			<td>${m.id}</td>
			<td>${m.name}</td>
			<td>${m.email }</td>
		</tr>
	</table>
</body>
</html>