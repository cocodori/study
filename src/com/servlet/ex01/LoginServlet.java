package com.servlet.ex01;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		//한 번만 실행
		System.out.println("init()........");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet().........");
		
		//doGet을 호출하면 login.html을 띄운다.
		response.sendRedirect("/login.html");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//전송 받은 데이터를 UTF-8로 인코딩
		request.setCharacterEncoding("utf-8");
		
		//하나의 name으로 여러 값을 전송하는 경우 getParameterValues()를 이용해 배열로 반환한다.
		String[] subject = request.getParameterValues("subject");
		
		//하나씩 전송된 값은 getParameter()를 이용한다
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		
		System.out.println("아이디 : " + id);
		System.out.println("비밀번호 : " + pw);
		System.out.println("subject : " + Arrays.toString(subject));
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy().......");
	}

}
