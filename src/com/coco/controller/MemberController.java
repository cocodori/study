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
		
		/* 요청 URL이 '/m/list'일 경우 
		 * 회원가입 GET
		 * */
		if(action.equals("/list")) { 
			//DB에서 받아온 회원 목록을 request스코프로 바인딩해서 화면으로 보낸다.
			request.setAttribute("memberList", dao.memberList());
			
			//현재 이 서블릿에 위치하고 있는 
			nextPage = "list";
			
		/* 요청 URL이 '/m/addMember'일 경우 
		 * 회원가입 POST 
		 * */
		} else if (action.equals("/addMember")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("email");
			String email = request.getParameter("email");
			MemberVO vo = new MemberVO(id, pwd, name, email);
			
			request.setAttribute("memberList", dao.memberList());
			
			nextPage = dao.addMember(vo) == 1 ? "list": "signup";

			/* 요청 URL이 '/m/signup'일 경우 */
		} else if(action.equals("/signup")) {
			nextPage = "signup";
		
			/* 요청 URL이 '/m/modify'일 경우 
			 * 회원 정보 수정 GET
			 * */
		} else if (action.equals("/modifyForm")){
			String id = request.getParameter("id");
			MemberVO memberInfo = dao.findMember(id);
			request.setAttribute("memberInfo", memberInfo);
			nextPage ="/modifyForm";
			
		/* 요청 URL이 '/m/modify'일 경우 
		* 회원정보 수정 POST
		* */
		} else if(action.equals("/modify")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			MemberVO vo = new MemberVO(id, pwd, name, email);
			
			System.out.println("vo : " + vo);
			dao.modMember(vo);

			nextPage = "list";
			
		} else if(action.equals("/remove")) { //회원 삭제
			String id = request.getParameter("id");
			dao.remove(id);
			nextPage = "list"; 
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/"+nextPage+".jsp");
		dispatcher.forward(request, response);
	} //doHandle()
}
