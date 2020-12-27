package pro08.sec03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro07.sec01.ex01.MemberVO;

@WebServlet("/viewMembers")
public class ViewServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doHandle(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		MemberDAO dao = new MemberDAO();
		List memberList = (List) request.getAttribute("membersList");
		String data="";
		
		data += "<html><body>";
		data += "<table><tr>";
		data += "<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td><td>삭제</td></tr>";
		
		for(int i=0; i < memberList.size(); i++) {
			MemberVO vo = (MemberVO) memberList.get(i);
			data += "<tr><td>"+vo.getId()+"</td><td>"+vo.getPwd()+"</td><td>"+vo.getName()+"</td><td>"+vo.getEmail()+"</td><td>"+vo.getRegdate()+"</td><td>"
					+"<a href='/member?command=delMember&id="+vo.getId()+"'>삭제</a></td></tr>";
		}
		
		data += "</table></body></html>";
		
		out.print(data);
		out.print("<a href='/signup.html'>회원가입</a>");
	}
}
