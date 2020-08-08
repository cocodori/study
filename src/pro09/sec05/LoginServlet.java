package pro09.sec05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//로그인한 아이디와 비밀번호 정보를 가져온다.
		String id = request.getParameter("id");
		String pwd = request.getParameter("password");
		
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		boolean result = false;
		
		//vo객체에 id, pw를 저장
		vo.setId(id);
		vo.setPwd(pwd);
		
		//해당 id가 있으면 ture를, 없으면 false를 반환한다.
		result = dao.isExisted(vo);
		
		if(result) {
			HttpSession session = request.getSession();
			session.setAttribute("isLogOn", true);	//세션에 isLogOn이라는 이름으로 true를 저장
			session.setAttribute("login.id", id);	//id와 pw를 세션에 저장.
			session.setAttribute("login.pwd", pwd);
			
			out.print("<html><body><h1>안녕하세요 "+id+"님!</h1><br><h3><a href='/show'>회원정보</a></h3></body></html>");
			
		} else {
			out.print("<html><body><h1>아이디가 틀립니다.</h1><br><h3><a href='/login.html'>다시</a></h3></body></html>");
		}
	}
}
