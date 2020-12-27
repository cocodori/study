<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답변 작성</title>
</head>
<body>
<h1>답변</h1>
<form name='action' method='post' action='/board/rePostRegister'>
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
				<input type='submit' value='답변 등록'>
				<input type='button' id='back' value='이전'>
			</td>
		</tr>
	</table>
	<input type="hidden" id='pbno' name='pbno' value='${bno }'>
</form>
<script>
	window.onload = () => {
		const back = document.getElementById('back')
		
		//'이전'버튼 누르면 원글로 돌아감
		back.addEventListener("click", () => {
			const pbno = document.getElementById('pbno')
			location.replace('/board/post?no='+pbno.value)					
		})
	}
</script>
</body>
</html>