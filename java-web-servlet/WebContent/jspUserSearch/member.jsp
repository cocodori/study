<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="pro12.sec01.*"
	import="java.util.*"    
%>

<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	MemberVO vo = new MemberVO();
	vo.setName(name);
	MemberDAO dao = new MemberDAO();
	List memberList = dao.listMembers(vo);
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<style type="text/css">
h1 {
	text-align:center;
}


tr {
	align:center;
	bgcolor:'#FFFF66';
}
	
	
</style>
</head>
<body>

<h1>요청하신 회원 정보</h1>
<table border=1 width=800 align=center>
	<tr align=center bgcolor='#FFFF66'>
		<td>아이디</td>
		<td>비밀번호</td>
		<td>이름</td>
		<td>이메일</td>
		<td>가입일</td>
	</tr>
	
	<%
	for (int i=0;i<memberList.size();i++){
		MemberVO member = (MemberVO) memberList.get(i);
		String id = member.getId();
		String pw = member.getPwd();
		String memberName = member.getName();
		String email = member.getEmail();
		Date regdate = member.getRegdate();%>
		
		<tr align="center">
			<td><%=id %></td>
			<td><%=pw %></td>
			<td><%=memberName %></td>
			<td><%=email %></td>
			<td><%=regdate %></td>
		</tr>
		
<%	} //end - for
	%>
</table>

</body>
</html>