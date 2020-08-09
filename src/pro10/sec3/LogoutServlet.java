package pro10.sec3;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/listenerLogout")
public class LogoutServlet extends HttpServlet {
	
	ServletContext context;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		context = getServletContext();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		//삭제할 ID가져온다.
		String id = request.getParameter("id");
		session.invalidate();	//로그아웃 시 세션 소멸
		
		ArrayList userList = (ArrayList)context.getAttribute("userList");
		
		userList.remove(id);
		
		//기존의 바인딩을 언바인딩하고, 수정된 userList를 바인딩
		context.removeAttribute("userList");
		context.setAttribute("userList", userList);
		out.println("<h1>로그아웃 했습니다.</h1>");
	}
}
