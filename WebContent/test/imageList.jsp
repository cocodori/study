<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 리스트 출력</title>
<style>
.listType {
	overflow:hidden;
	width:80%;
	padding:0 10px 10px;
	margin:0 auto
}
.listType li{
	overflow:hidden;
	clear:both;
	margin:10px 0 0;
	color:#2d2c2d;
	font-family:'돋움', Dotum;
	font-size:12px;
	line-height:100px;
	list-style:none;
	border-bottom: 2px solid lightgray;
	position:relative;
}
.listType li img {
	display:inline;
	float:left;
	position:absolute;
}
.listType li a {
	color:#2d2c2d;
	text-decoration:none;
	margin-left:350px
}
.listType li a:hover {text-decoration:underline}
.listType li span {
	color:blue;
	margin-left:300px;
	font-family:'돋움',Dotum;
	font-size:14px;
}
</style>
</head>
<body>
<ul class='listType'>
	<li>
		<span style='margin-left:50px'>이미지</span>
		<span>이미지 이름</span>
		<span>선택하기</span>
	</li>
	<%
	for(int i=0; i<10; i++) {%>
	<li>
		<a href='#' style='margin-left:50px'>
		<img src='/image/walle.png' width='90' height='90' alt=''/></a>
		<a href='#'><strong><%=i%>. 월-E</strong></a>
		<a href='#'><input name='chk<%=i %>' type='checkbox'></a>
	</li>
<% 	}%>
</ul>
</body>
</html>