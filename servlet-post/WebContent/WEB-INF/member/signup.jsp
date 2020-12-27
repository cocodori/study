<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign up</title>
</head>
<body>
<h1 style='text-align:center'>회원가입</h1>

<form method='post' action='/m/addMember'>
	<table align='center'>
		<tr>
			<td width='200'>
				<p align='right'>아이디</p>
			</td>
			<td width='400'>
				<p><input type='text' name='id'></p>
			</td>
		</tr>
		<tr>
			<td>
				<p align='right'>비밀번호</p>
			</td>
			<td width='400'>
				<p><input type='password' name='pwd'> </p>
			</td>
		</tr>
		<tr>
			<td width='200'>
				<p align='right'>이름</p>
			</td>
			<td width='400'>
				<p><input type='text' name='name'></p>
			</td>
		</tr>
		<tr>
			<td width='200'>
				<p align='right'>Email</p>
			</td>
			<td width='400'>
				<p><input type='text' name='email'></p>
			</td>
		</tr>
		<tr>
			<td width='200'>
				<p>&nbsp;</p>
			</td>
			<td>
				<p><input type='submit' value='가입'></p>
			</td>
		</tr>
	</table>
</form>
</body>
</html>