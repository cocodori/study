package pro07.sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/member2")
public class MemberServlet2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		MemberDAO dao = new MemberDAO();
		List list = dao.listMembers();
		
		String data = "";
		data += "<html><body>";
		data += "<table border=1><tr align='center' bgcolor='lightgreen'>";
		data += "<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td></tr>";
		
		for (int i=0; i<list.size();i++) {
			MemberVO vo = (MemberVO) list.get(i);
			data +="<tr><td>"+vo.getId()+"</td>"
					+"<td>"+vo.getPwd()+"</td>"
					+"<td>"+vo.getName()+"</td>"
					+"<td>"+vo.getEmail()+"</td>"
					+"<td>"+vo.getRegdate()+"</td></tr>";
		}
		
		data += "</table></body></html>";
		out.print(data);
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
