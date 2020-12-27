<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INCLUDE2</title>
</head>
<body>
	<h1>폐허한 지구</h1>
	<jsp:include page="/actionEx/walleImage.jsp" flush="true">
		<jsp:param name="name" value="EVE"/>
		<jsp:param name="imgName" value="eve.png"/>
	</jsp:include>
</body>
</html>