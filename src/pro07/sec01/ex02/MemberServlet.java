package pro07.sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro07.sec01.ex01.MemberVO;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		MemberDAO dao = new MemberDAO();
		PrintWriter out = response.getWriter();
		String command = request.getParameter("command");
		
		System.out.println("command : " + command);
		
		if (command != null && command.equals("addMember")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			MemberVO vo = new MemberVO();
			
			vo.setId(id);
			vo.setPwd(pwd);
			vo.setName(name);
			vo.setEmail(email);

			//등록
			dao.addMember(vo);
		} else if(command!=null&&command.equals("delMember")) {
			String id = request.getParameter("id");
			dao.delMember(id);
		} //if
		
		List list = dao.listMembers();
		String data="";
		data+="<html><body>";
		data+="<table border=1><tr align='center' bgcolor='lightgreen'>";
		data+="<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td><td>삭제</td><tr>";
		
		for(int i=0;i<list.size();i++) {
			MemberVO vo = (MemberVO) list.get(i);
			String id = vo.getId();
			String pwd = vo.getPwd();
			String name = vo.getName();
			String email = vo.getEmail();
			Date regdate = vo.getRegdate();
			
			data+="<tr><td>"+id+"</td>"
					+"<td>"+pwd+"</td>"
					+"<td>"+name+"</td>"
					+"<td>"+email+"</td>"
					+"<td>"+regdate+"</td></tr>";
		}
		
		data+="</table></body></html>";
		out.print(data);
	} //doHandle()

}
