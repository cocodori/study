<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<style>
.cls1 {
	text-decoration:none;
}

.cls2 {
	text-align:center;
	font-size:30px;
}
</style>
</head>
<body>
<h1 align='center'>자유 게시판</h1>
<table align='center' border='1' width='80%'>
	<tr height='10' align='center' bgcolor='lightgreen'>
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>작성일</td>
	</tr>
	<c:choose>
		<c:when test='${boardList == null}'>
			<td colspan="4">
				<p align="center">
					<b><span style='font-size=9pt;'>등록된 글이 없습니다.</span></b>
				</p>
			</td>
		</c:when>
		
		<c:when test="${boardList != null }">
			<c:forEach var="post" items="${boardList }" varStatus="postNum">
			<tr align="center">
				<td width="5%">${postNum.count }</td>
				<td width="10%">${post.id }</td>
				<td align='left' width="35%">
				<span style='padding-right:30px'></span>
			<c:choose>
				<c:when test="${post.level > 1 }">
					<c:forEach begin="1" end="${post.level }">
						<span style='padding-left:20px'></span>
					</c:forEach>
					<span style='font-size:12px;'>[답변]</span>
					<a class="cls1" href='/board/post?no=${post.bno }'>${post.title }</a>
				</c:when>
				
				<c:otherwise>
					<a class='cls1' href='/board/post?no=${post.bno }'>${post.title }</a>
				</c:otherwise>
			</c:choose>
			</td>
			<td width="10%">
				<fmt:formatDate value="${post.regdate }"/>
			</td>
			</tr>
			</c:forEach>
		</c:when>
	</c:choose>
</table>
<a class='cls1' href='#'><p class='cls2'>글쓰기</a>
</body>
</html>