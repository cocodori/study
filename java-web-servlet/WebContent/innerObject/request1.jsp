<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="javax.servlet.RequestDispatcher"
%>

<%
	request.setAttribute("name", "이모라");
	request.setAttribute("address", "몰라몰라컴퍼니");
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("/innerObject/request2.jsp");
	dispatcher.forward(request, response);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>