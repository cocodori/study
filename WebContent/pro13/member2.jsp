<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="pro13.sec01.*" import="java.util.*"%> 
<%
	request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id='member' class='pro13.sec01.MemberBean' scope='page' />

<jsp:setProperty name="member" property="*"/>

<%
	MemberDAO dao = new MemberDAO();
	dao.addMember(member);
	List<MemberBean> memberList = dao.memberList();
%>


<!-- 아이디가 'member'인 MemberBean객체를 만든다. -->


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 목록</h1>
	<table align='center' width='100%'>
		<tr align='center' bgcolor='#99ccff'>
			<td width='7%'>아이디</td>
			<td width='7%'>비밀번호</td>
			<td width='5%'>이름</td>
			<td width='11%'>이메일</td>
		</tr>
		<tr align='center'>
			<td>
				<jsp:getProperty property="id" name="member"/>
			</td>
			<td>
				<jsp:getProperty property="pwd" name="member" />
			</td>
			<td>
				<jsp:getProperty property="name" name="member" />
			</td>
			<td>
				<jsp:getProperty property="email" name="member"/>
			</td>
		</tr>
	
	<tr height='1' bgcolor='#99ccff'>
		<td colspan='5'></td>
	</tr>
	</table>
</body>
</html>