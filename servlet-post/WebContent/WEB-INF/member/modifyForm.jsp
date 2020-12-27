<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
</head>
<body>
	<h1 align='center'>회원 정보 수정</h1>
	<form method='post' action='/m/modify'>
	<table align='center'>
		<tr align='center'>
			<td width='400'><p align="right">아이디 <input type='text' name='id' value='${memberInfo.id }' readonly></td>
		</tr>
		<tr align='center'>
			<td width='400'><p align="right">비밀번호 <input type='password' name='pwd' ></td>
		</tr>
		<tr align='center'>
			<td width='400'><p align="right">이름 <input type='text' name='name' ></td>
		</tr>
		<tr align='center'>
			<td width='400'><p align="right">이메일 <input type='text' name='email' ></td>
		</tr>
		<tr align='center'>
			<td width='400'><p align="right">가입일 <input type='text' name='regdate' value='${memberInfo.regdate }' disabled></td>
		</tr>
		<tr align='center'>
			<td><input type='submit' value='수정'></td>
		</tr>
	</table>
	</form>
</body>
</html>