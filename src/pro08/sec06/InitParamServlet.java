package pro08.sec06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "initParamServlet", urlPatterns = {"/sinit", "/sinit2"}
			, initParams = {
					@WebInitParam(name = "email", value="admin@web.com")
					,@WebInitParam(name="tel", value = "000-0000-0000")
			})
public class InitParamServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String email = getInitParameter("email");
		String tel = getInitParameter("tel");
		
		System.out.println("tel :" + tel);
		System.out.println("email : " + email);
	}

}
