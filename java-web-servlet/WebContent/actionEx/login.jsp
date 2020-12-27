<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	//String msg = (String)request.getAttribute("msg");
	String msg = request.getParameter("msg");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forward action tag</title>
</head>
<body>
<%
	if(msg != null) { %>
		<h1><%=msg %></h1>
<%	} %>
	<form action='/actionEx/result.jsp'>
		<input type='text' name='id' placeholder='ID...'><br>
		<input type='password' name='pwd' placeholder='PASSWORD...'>
		<input type='submit' value='LOGIN'>
	</form>
</body>
</html>