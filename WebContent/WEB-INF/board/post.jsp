<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[${post.bno}번 게시물] ${post.title }</title>
</head>
<body>
<h1>게시물 조회</h1>
<form action="/board/mod">
	<table>
		<tr>
			<td width="20%" align="center"> 제목 </td>
			<td> <input type='text' name='title' value='${post.title }' disabled></td>
		</tr>
		<tr>
			<td width="20%" align="center"> 내용 </td>
			<td>
				<textarea rows="20" cols="60" name='content' disabled>${post.content }</textarea>
			</td>
		</tr>
		<tr>
			<td width="20%" align="center"> 작성자 </td> 
			<td><input type='text' name='id' value='${post.id }' disabled></td> 
		</tr>
		<tr>
			<td align="right"><input type='submit' value='수정'>
			<input type='button' id='remove' value='삭제'> 
			<input type='button' id='list' value='목록'>
			<input type='button' id='rePost' value='답글'></td> 
		</tr>
	</table>
	
	<input type='hidden' id='bno' name='bno' value='${bno }'>
</form>
<script>
	window.onload = () => {
		const remove = document.getElementById('remove')
		const list = document.getElementById('list')
		const rePost = document.getElementById('rePost')

		console.log(remove)
		console.log(list)
		console.log(rePost)
		
		list.addEventListener("click", () => {
			location.replace('/board/list')
		})
		
		remove.addEventListener("click", () => {
			if(!confirm("삭제하겠습니까?")) { return; }
			else{
				//....
			}
		})
	}
</script>
</body>
</html>