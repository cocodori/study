package pro06.se04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/loginTest")
public class LoginTest extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("username");
		String pw = request.getParameter("password");
		
		System.out.println(" ID : " + id);
		System.out.println(" PW : " + pw);
		
		String outData= "";
		
		if(id != null && (id.length() != 0)) {
			outData += "<html><body>";
			outData += id+"님이 로그인하셨습니다.";
			outData += "</body></html>";
			out.print(outData);
		} else {
			outData += "<html><body>";
			outData += "아이디를 입력하세요";
			outData += "  <a href='http://localhost:8080/test01/login.html'>로그인 창으로 이동</a>";
			outData += "</body></html>";
			
			out.print(outData);
		} // if - else
		
	} //doPost()
} //end - class
