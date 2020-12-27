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
<form id='action'>
	<table>
		<tr>
			<td width="20%" align="center"> 제목 </td>
			<td> <input type='text' id='title' name='title' value='${post.title }' disabled></td>
		</tr>
		<tr>
			<td width="20%" align="center"> 내용 </td>
			<td>
				<textarea rows="20" cols="60" id='content' name='content' disabled>${post.content }</textarea>
			</td>
		</tr>
		<tr>
			<td width="20%" align="center"> 작성자 </td> 
			<td><input type='text' id='id' name='id' value='${post.id }' disabled></td> 
		</tr>
		<tr id="modBtn" style='display:none;'>
	    	<td colspan="2"   align="center" >
	        <input type=button id='mod' value="수정 완료">
         	<input type=button id='back' value="취소">
	   		</td>   
 		</tr>
		<tr id="btn">
			<td colspan="2" align="center">
			<input type='button' id='modify' value='수정'>
			<input type='button' id='remove' value='삭제'> 
			<input type='button' id='list' value='목록'>
			<input type='button' id='rePost' value='답변'></td> 
		</tr>
	</table>
	
	<input type='hidden' id='bno' name='bno' value='${post.bno }'>
	</form>
	
<script>
	window.onload = () => {
		const remove = document.getElementById('remove')
		const list = document.getElementById('list')
		const rePost = document.getElementById('rePost')
		const modify = document.getElementById('modify')
		const actionForm = document.getElementById('action')
		const id = document.getElementById('id')
		
		//답글
		document.getElementById('rePost').addEventListener("click", () => {
			actionForm.method = 'post'
			actionForm.action = '/board/rePost'
			actionForm.submit()
			
		})
		
		if(id.value.length==0||id==='') {
			document.getElementById('modify').style.display='none'		
			document.getElementById('rePost').style.display='none'		
			document.getElementById('remove').style.display='none'		
		}

		//목록으로
		list.addEventListener("click", () => {
			location.replace('/board/list')
		})
		
		//수정 버튼 이벤트
		modify.addEventListener("click", ()=>{
			document.getElementById('title').disabled=false
			document.getElementById('content').disabled=false
			document.getElementById("modBtn").style.display="block"
			document.getElementById('btn').style.display="none"
			
		})
		
		//수정 완료
		document.getElementById('mod').addEventListener("click", () => {
			actionForm.action = '/board/modify'
			actionForm.method = 'post'
			actionForm.submit();
		})
		
		//수정 취소
		document.getElementById('back').addEventListener("click", () => {
			document.getElementById('btn').style.display="block"
			document.getElementById("modBtn").style.display="none";
			document.getElementById('content').disabled=true
			document.getElementById('title').disabled=true
		})
		
		//삭제
		remove.addEventListener("click", () => {
			if(!confirm("삭제하겠습니까?")) { return; }
			else{
				actionForm.action = '/board/remove'
				actionForm.method = 'post'
				actionForm.submit()
			}
		}) 
	}
</script>
</body>
</html>