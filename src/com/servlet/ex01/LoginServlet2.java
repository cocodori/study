package com.servlet.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet2
 */
@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩 설정
		request.setCharacterEncoding("utf-8");
		//MIME-TYPE설정 응답할 데이터 종류가 html임을 미리 알림.
		response.setContentType("text/html;charset=utf-8");
		//HttpServletResponse객체의 getWriter()를 이용해 출력 스트림 PrintWriter 객체를 받아온다.
		PrintWriter out = response.getWriter();

		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		
		String data = "<html>";
		data += "<body>";
		data += "아이디 : " +id;
		data += "<br>";
		data += "비밀번호 : " + pw;
		data += "</body>";
		data += "</html>";
		
		out.print(data);
	}


	public void destroy() {
		System.out.println("destroy()...");
	}

}
