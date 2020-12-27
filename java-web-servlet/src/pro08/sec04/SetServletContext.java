package pro08.sec04;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/cset")
public class SetServletContext extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//ServletContext객체 생성
		ServletContext context = getServletContext();
		
		List member = new ArrayList();
		
		member.add("김빵빵");
		member.add(30);
		
		context.setAttribute("member", member);
		
		out.print("<html><body><h1>김빵빵 - 30을 List에 담은 뒤 ServletContext에 member로 바인딩했다.</h1></body></html>");
		
	}

}
