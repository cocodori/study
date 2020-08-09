package pro10.sec01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/geta")
public class GetAttribute extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		ServletContext ctx = getServletContext();
		HttpSession session = request.getSession();

		String contextMsg = (String)ctx.getAttribute("context");
		String sessionMsg = (String)session.getAttribute("session");
		String requestMsg = (String)request.getAttribute("request");
		
		out.print("context : " + contextMsg + "<br>");
		out.print("session : " + sessionMsg + "<br>");
		out.print("request : " + requestMsg + "<br>");
	}
}
