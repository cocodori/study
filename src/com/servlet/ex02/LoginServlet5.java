package com.servlet.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/login5")
public class LoginServlet5 extends HttpServlet {
	
	public void init() {
		System.out.println("맨 처음 한 번만 실행되는 init()");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		String hidden = request.getParameter("hiddenData");
		
		System.out.println(" ID : " + id);
		System.out.println(" PW : " + pw);
		
		String data = "<html>";
		data += "<body>";
		data += "ID : " + id;
		data += "<br>";
		data += "PW : " + pw;
		data += "<br>";
		data += "hidden : " + hidden;
		data += "</body>";
		data += "</html>";
		
		out.print(data);	//전송 받은 값을 브라우저에 출력한다.
		
	}
	
	public void destroy() {
		System.out.println("마지막 한 번만 실행되는 destroy()");
	}

}
