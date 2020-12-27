package pro09.sec04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/st5")
public class SessionTest5 extends HttpServlet {
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
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		
		if(session.isNew()) { //새로 생성된 세션이면 true 아니면 false
			if(id != null) {
				//HttpSession객체에 id 바인딩
				session.setAttribute("id", id);
				//응답 시, 미리 JSESSIONID를 저장한다.
				String url = response.encodeURL("st5");
				//주어진 url로 이동할 때, jsessionid가 get방식으로 함께 날아간다.
				out.println("<a href='"+url +"'>로그인 상태 확인</a>");
			} else {
				out.print("<a href='/login.html'>다시 로그인 하세요</a>");
				session.invalidate(); //세션 삭제
			}
		} else {
			id = (String) session.getAttribute("id");
			if(id != null && id.length() != 0 ) {
				out.print("<h1>안녕하세요 "+id+"님!</h1>");
			} else {
				out.print("<a href='/login.html'>뭔가 잘못 됐겠죠.</a>");
			}
		}
		
	}
}
