<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! /* 선언문 */
	private String name = "뽀로로";
	public String getName() {
		return this.name;
	}
%>

<% /* 스크립트릿 */
	String age = request.getParameter("age");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>이름 <%=this.name%>, 나이 <%=age%></h1>
<%=Integer.parseInt(age)+10 %>
</body>
</html>