package pro09.sec01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/scook")
public class SetCookieValue extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Date date = new Date();
		
		//쿠키 객체를 생성한 후, cookTest라는 이름으로 한글 정보를 인코딩해서 쿠키에 저장한다.
		Cookie cookie = new Cookie("cookieTest", URLEncoder.encode("JSP PROGRAMMING", "utf-8"));

		//응답에 쿠키를 포함한다.
		response.addCookie(cookie);
		
		out.println("현재 시간 : " + date);
		out.print("현재시간을 쿠키로 저장한다.");
	}
}
