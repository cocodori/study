<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%> 
<%!
	private String name = "코코";
	public String getName() {
		return this.name;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<h1>Hello JSP!</h1>
<h2>안녕하세요 <%=getName()%>님~</h2>
</body>
</html>