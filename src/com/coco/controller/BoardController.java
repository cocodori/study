package com.coco.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coco.service.BoardService;
import com.coco.service.BoardServiceImpl;
import com.coco.vo.BoardVO;
import com.test.dao.JDBCTest;

//'/board' 경로로 들어오는 모든 요청은 이 컨트롤러를 거친다.
@WebServlet("/board")
public class BoardController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService boardService = new BoardServiceImpl();
		String nextPage = "";
		String action = request.getPathInfo();	//어떤 url을 요청했는가?
		List<BoardVO> list;
		System.out.println("action : " + action);
		
		/* 클라이언트가 요청한 URL이 '/board' 또는 '/board/list'일 경우
		 * service에서 게시글 목록을 불러오는 메서드를 바인딩 해서 화면으로 보낸다.
		 */
		if(action == null || action == "/list") {
			//request.setAttribute("boardList", boardService.getList());
			nextPage="list";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/board/"+nextPage+".jsp");
		dispatcher.forward(request, response);
	}
}
