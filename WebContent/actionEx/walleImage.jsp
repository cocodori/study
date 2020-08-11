<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String imgName = request.getParameter("imgName");	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>8 BIT WALL-E</title>
</head>
<body>
<h1>이름은 <%=name %></h1>
</body><img src="/image/<%=imgName %>" />
</html>