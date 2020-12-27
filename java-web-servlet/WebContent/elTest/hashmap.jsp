<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="pro13.sec01.MemberBean, java.util.*"
    %>

<%
	request.setCharacterEncoding("utf-8");
%>

<!-- MemberBean객체 생성. 파라미터로 들어오는 값을, MemberBean 속성에 맞춰서 매핑 -->
<jsp:useBean id="m1" class="pro13.sec01.MemberBean" />
<jsp:setProperty name="m1" property="*"/>
<!-- ArrayList, HashMap 생성-->
<jsp:useBean id="memberList" class="java.util.ArrayList" />
<jsp:useBean id="memberMap" class="java.util.HashMap" />

<%
	//map객체에 회원 정보 저장
	memberMap.put("id", "jojo");
	memberMap.put("pwd", "1234");
	memberMap.put("name", "김조조");
	memberMap.put("email", "jojo@naver.com");
	
	//MemberBean객체 생성하고, 초기화
	MemberBean m2 = new MemberBean("kal", "1234","김갈량","kal@daum.com");
	
	//리스트에 담는다.
	memberList.add(m1);
	memberList.add(m2);
	
	//리스트를 맵에 담는다.
	memberMap.put("memberList", memberList);
	System.out.println("memberList : " + memberList);
	System.out.println("memberMap.memberList[1].id: " + memberMap.get("memberList"));
	

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL로 HashMap다루기</title>
</head>
<body>
<table border='1' align='center'>
		<tr align='center' bgcolor='#99ccff'>
			<td width='20%'><b>아이디</b></td>
			<td width='20%'><b>비밀번호</b></td>
			<td width='20%'><b>이름</b></td>
			<td width='20%'><b>이메일</b></td>
		</tr>
		<tr align='center'>
		<!-- map객체에 매핑한 회원 정보 -->
			<td>${memberMap.id }</td>
			<td>${memberMap.pwd }</td>
			<td>${memberMap.name }</td>
			<td>${memberMap.email }</td>
		</tr>
		<tr align='center'>
		<!-- 회원가입 페이지로부터 받은 회원 정보 -->
			<td>${memberMap.memberList[0].id }</td>
			<td>${memberMap.memberList[0].pwd }</td>
			<td>${memberMap.memberList[0].name }</td>
			<td>${memberMap.memberList[0].email }</td>
		</tr>
		<tr align='center'>
			<td>${memberMap.memberList[1].id }</td>
			<td>${memberMap.memberList[1].pwd }</td>
			<td>${memberMap.memberList[1].name }</td>
			<td>${memberMap.memberList[1].email }</td>
		</tr>
	</table>
</body>
</html>