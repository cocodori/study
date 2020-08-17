<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 등록</title>
</head>
<body>
<h1>글쓰기</h1>
<form name='action' method='post' action='/board/register'>
	<table>
		<tr>
			<td align="right"> 제목 </td>
			<td clspan='2'><input type='text' name='title' size='67' maxlength="50"></td>
		</tr>
		<tr>
			<td align="right" valign="top"><br> 내용 </td>
			<td colspan="2"><textarea rows="10" cols="65" name='content'></textarea></td>
		</tr>
		<tr>
			<td align="right"> 작성자 </td>
			<td clspan='2'><input type='text' name='id' maxlength="50"></td>
		</tr>
		<tr>
			<td align="right"> </td>
			<td colspan="2">
				<input type='submit' value='글쓰기'>
				<input type='button' id='list' value='목록'>
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	window.onload = () => {
		const list = document.getElementById('list')
		
		list.addEventListener("click", () => {
			location.replace('/board/list')
		})
	}
</script>
</body>
</html>