<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body>
	<c:choose>
		<c:when test="${empty memberList }">
			<h1>등록된 회원이 없습니다.</h1>
		</c:when>
	<c:otherwise>
		<table border='1' align='center'>
			<tr align='center' bgcolor='#99ccff'>
				<td width='20%'><b>아이디</b></td>
				<td width='20%'><b>이름</b></td>
				<td width='20%'><b>이메일</b></td>
				<td width='20%'><b>가입일</b></td>
				<td><b>수정</b></td>
				<td><b>삭제</b></td>
			</tr>
			<c:forEach var="member" items="${memberList}">
			<tr align="center">
				<td>${member.id }</td>
				<td>${member.name }</td>
				<td>${member.email }</td>
				<td>${member.regdate }</td>
				<td><a href='/m/modifyForm?id=${member.id }'>수정</a></td>
				<td><a href='/m/remove?id=${member.id }'>삭제</a></td>
			</tr>
			</c:forEach>
		</table>
	</c:otherwise>
	</c:choose>
<center><a href='/m/signup'>회원가입</a></center>
</body>
</html>