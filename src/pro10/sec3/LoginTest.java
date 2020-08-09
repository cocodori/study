package pro10.sec3;

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
import javax.servlet.http.HttpSession;

@WebServlet("/listenerLogin")
public class LoginTest extends HttpServlet {
	ServletContext context;
	
	//로그인한 접속자 ID를 저장할 List
	List<String> userList = new ArrayList<>();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		context = getServletContext();
		String id = request.getParameter("id");
		String pwd = request.getParameter("password");
		
		//로그인한 아이디와 비밀번호로 LoginImpl객체 생성
		LoginImpl loginUser = new LoginImpl(id, pwd);
		
		/* 최초 로그인 시, ID를 List에 저장하고, context객체에 바인딩한다. */
		if (session.isNew()) { // 새로 생성된 세션일 경우
			session.setAttribute("loginUser", loginUser);
			System.out.println("id : " + id );
			userList.add(id);
			System.out.println("userList : " + userList);
			context.setAttribute("userList", userList);
		}
		
		out.print("<html><head></head><body><h1>ID : " + id + "<br>PW : " + pwd + "<br>접속자 수 : " + LoginImpl.totalUser+"</h1>"
				+"<h2>접속자 아이디 : <br>");
		
		List list = (ArrayList)context.getAttribute("userList");
		for(int i=0; i<list.size();i++) {
			out.print(list.get(i)+"<br>");
		}
		out.println("<a href='/listenerLogout?id="+id+"'>로그아웃</a>");
		//JS의 setTimeout함수를 이용하여 5초마다 서블릿에 재요청하며 현재 접속자수를 파악
		out.print("<script type='text/javascript'> setTimeout('history.go(0);',5000); </script></body></html>");
	}

}
