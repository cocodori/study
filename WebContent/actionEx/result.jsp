<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	if(id.length() == 0 || id == null) {
		//RequestDispatcher dispatch = request.getRequestDispatcher("/actionEx/login.jsp");
		//dispatch.forward(request, response);
		//request.setAttribute("msg", "아이디를 입력하세요.");
		String msg = "아이디를 입력하세요.";

%>
	<jsp:forward page='/actionEx/login.jsp' >
		<jsp:param name='msg' value='<%=msg %>' />
		
	</jsp:forward>

<%	}//if%>

<h1>환영합니다. <%=id %>님!</h1>

</body>
</html>