package pro09.sec05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/show")
public class ShowMember extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String id = "", pwd = "";
		Boolean isLogOn = false;
		
		//이미 세션이 존재하면 세션을 반환, 없으면 null.
		HttpSession session = request.getSession(false);
		
		if(session != null) { //세션이 생성 됐는지 확인
			//LogOn속성을 가져와 로그인상태 확인
			isLogOn = (Boolean)session.getAttribute("isLogOn");
			
			if(isLogOn) {
				//Session에서 id와 pwd값을 가져온다.
				id = (String)session.getAttribute("login.id");
				pwd = (String)session.getAttribute("login.pwd");
				out.print("<html><body>아이디 : "+id+"<br>비밀번호 : "+pwd+"</body></html>");
			} else {
				response.sendRedirect("/login.html");
			}
		} else {
			response.sendRedirect("/login.html");
		}
	}
}
