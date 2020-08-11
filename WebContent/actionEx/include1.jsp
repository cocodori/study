<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include1</title>
</head>
<body>
	<h1>폐허한 지구의 두 친구</h1>
	
	<jsp:include page="walleImage.jsp" flush="true">
		<jsp:param name='name' value='WALL-E'/>
		<jsp:param name='imgName' value='walle.png'/>
	</jsp:include>
		
</body>
</html>