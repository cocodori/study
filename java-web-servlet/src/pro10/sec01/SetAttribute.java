package pro10.sec01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/seta")
public class SetAttribute extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		String contextMsg = "context에 바인딩 됩니다.";
		String sessionMsg = "session에 바인딩 됩니다.";
		String requestMsg = "request에 바인딩 됩니다.";
		
		ServletContext ctx = getServletContext();
		HttpSession session = request.getSession();
		
		ctx.setAttribute("context", contextMsg);
		session.setAttribute("session", sessionMsg);
		request.setAttribute("request", requestMsg);
		
		out.print("binding.....");
		
		response.sendRedirect("/geta");
	}
}
