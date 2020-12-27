package pro06.se04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginTest2
 */
//@WebServlet("/loginTest2")
public class LoginTest2 extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		System.out.println("hello...init()...");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("username");
		String pw = request.getParameter("password");
		
		System.out.println(" ID : " + id);
		System.out.println(" PW : " + pw);
		
		String data = "";
		
		if(id!=null&&(id.length()!=0)) {
			if(id.equals("admin")) {
				data += "<html><body>";
				data += "<h1> 관리자 계정으로 로그인 하셨습니다.</h1>";
				data += "<input type='button' value='회원정보 등록'/>";
				data += "<input type='button' value='회원정보 수정'/>";
				data += "<input type='button' value='회원정보 삭제'/>";
				data += "</body></html>";
				
				out.print(data);
			} else {
				data += "<html><body>";
				data += "<h1>"+id+"님이 로그인 하셨습니다.</h1>";
				data += "</body></html>";
				
				out.print(data);
			}
		} else {
			data += "<html><body>";
			data += "<h1>아이디를입력하세요.</h1> <br> <a href='http://localhost:8080/test01/login.html'>돌아가기</a>";
			data += "</body></html>";
			
			out.print(data);
		}
	}

}
