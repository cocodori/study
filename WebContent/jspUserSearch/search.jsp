<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 이름으로 검색</title>

</head>
<body>
	<h1>찾고 싶은 회원의 이름을 입력하세요</h1>
	<form method='post' action='/jspUserSearch/member.jsp'>
		<input type='text' name='name' placeholder="브람스를 좋아하세요..?" >
		<input type='submit' value='검색'>
	</form>
</body>
</html>