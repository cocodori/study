package pro09.sec02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/st")
public class SessionTest extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//HttpSession객체 생성
		HttpSession session = request.getSession();
	
		out.println("세션 아이디 :" + session.getId()+"<br>");
		out.println("세션 생성 시간 : " + new Date(session.getCreationTime())+"<br>");
		out.println("최근 세션 접근 시각  : " + new Date(session.getLastAccessedTime())+ "<br>");
		//세션의 유효기간을 5초로 한다.
		session.setMaxInactiveInterval(5);
		out.println("세션 유효 시간 : " + session.getMaxInactiveInterval() +"<br>");
		
		if (session.isNew()) {
			out.print("새 세션이 만들어졌습니다.");
		}
	}
}
