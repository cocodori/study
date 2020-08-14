package com.coco.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coco.dao.MemberDAO;
import com.coco.vo.MemberVO;

@WebServlet("/m/*")
public class MemberController extends HttpServlet {
	private MemberDAO dao;
	
	public void init() throws ServletException {
		dao = new MemberDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		//URL에서 요청명을 가져온다.
		String action = request.getPathInfo();
		System.out.println("action : " + action);
		
		if(action.equals("/list")) {
			request.setAttribute("memberList", dao.memberList());
			nextPage = "list.jsp";
			
		} else if (action.equals("/addMember")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("email");
			String email = request.getParameter("email");
			MemberVO vo = new MemberVO(id, pwd, name, email);
			
			dao.addMember(vo);
			
			request.setAttribute("memberList", dao.memberList());

			
			nextPage = "list.jsp";
			
		} else if(action.equals("/signup")) {
			nextPage = "signup.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/"+nextPage);
		dispatcher.forward(request, response);
		
		
		
	} //doHandle()
	

}
