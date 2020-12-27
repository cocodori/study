package pro09.sec03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "SessionTest", urlPatterns = { "/st2" })
public class SessionTest extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//세션 객체 생성
		HttpSession session = request.getSession();
		//id, pw값을 받아온다.
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		
		System.out.println("id : " + id);
		System.out.println("pw : " + pw);
		
		if (session.isNew()){ //새 세션이라면,
			if(id != null){ //로그인 상태라면,
				session.setAttribute("id", id);	//세션에 id라는 이름으로 id를 바인딩한다.
				out.println("<a href='st2'>로그인 상태 확인</a>");	//다시 st2로 들어온다.
			}else {
				out.print("<a href='login.html'>다시 로그인 하세요!!</a>"); //로그아웃 상태라면 다시 로그인 창으로 돌려보낸다.
				session.invalidate();
			}
			
		} else { // 새 세션이 아닐 때 들어온다.
			id = (String) session.getAttribute("id"); //세션에 id라고 바인딩된 값을 받아온다.
			if (id != null && id.length() != 0) {	//해당 id가 있을 경우,
				out.print("안녕하세요 " + id + "님!!!");
			} else { //없다면 session을 지우고 login창으로 돌려보낸다.
				out.print("<a href='login2.html'>다시 로그인 하세요!!</a>");
				session.invalidate();
			}
		}
	}
}
