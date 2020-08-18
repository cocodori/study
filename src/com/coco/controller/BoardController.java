package com.coco.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private final static Logger log = Logger.getGlobal();
	
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
		log.info("action : " + action);
		
		/* 클라이언트가 요청한 URL이 '/board' 또는 '/board/list'일 경우
		 * service에서 게시글 목록을 불러오는 메서드를 바인딩 해서 화면으로 보낸다.
		 */
		if(action == null || action.equals("/list")) {
			request.setAttribute("boardList", boardService.getList());
			nextPage="/WEB-INF/board/list.jsp";
		} else if(action.equals("/write")) {
			nextPage = "/WEB-INF/board/write.jsp";
			
		}  else if (action.equals("/register") || action.equals("/rePostRegister")) {
			//등록한 게시물 데이터를 받아온다.
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String imgName = request.getParameter("imgName");
			String id = request.getParameter("id");
			int p_bno = Integer.parseInt(request.getParameter("pbno"));
			
			//받아온 데이터로 VO객체를 생성한다.
			BoardVO vo = new BoardVO();
			
			vo.setTitle(title);
			vo.setContent(content);
			vo.setImgName(imgName);
			vo.setId(id);
			vo.setP_bno(p_bno);
			
			log.info("vo : " + vo);
			
			//데이터베이스에 저장하는 메서드에 vo를 전달한다.
			//결과가 1이라면 성공, 그 외는 실패..
			nextPage = boardService.register(vo) == 1
							? "/board/list" : "error";
			
		} else if(action.equals("/post")) {
			//조회한 게시물 번호
			int no = Integer.parseInt(request.getParameter("no"));
			
			//게시물을 조회하는 메서드에 게시물 번호를 인자로 넘기고, 결과를 받는다.
			request.setAttribute("post", boardService.getPost(no));

			nextPage="/WEB-INF/board/post.jsp";
		} else if(action.equals("/modify")) {
     		//수정한 데이터를 받는다.
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int bno = Integer.parseInt(request.getParameter("bno"));
			BoardVO vo = new BoardVO();

			//수정한 데이터로 vo초기화
			vo.setBno(bno);
			vo.setTitle(title);
			vo.setContent(content);
			
			log.info("VO : " + vo);
			
            //수정하는 메서드 호출
			boardService.modify(vo);
			
            //내가 수정한 게시물로 이동
			nextPage = "/board/post?no="+bno;
		} else if (action.equals("/remove")) {
			int bno = Integer.parseInt(request.getParameter("bno"));
			log.info("bno : " + bno);

			int removeResult = boardService.remove(bno);
			
			String msg = removeResult==1 ? "success" : "fail";
			
			//list페이지로 성공 여부 메세지를 보낸다.
			//성공하면 list창에 alert창을 띄워 삭제했다는 메세지를 띄운다.
			request.setAttribute("result", msg);
			nextPage ="/board/list";
		} else if (action.equals("/rePost")) {
			int bno = Integer.parseInt(request.getParameter("bno"));
			log.info("bno : " +bno);
			request.setAttribute("bno", bno);
			
			nextPage="/WEB-INF/board/rePost.jsp";
		} 
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}
}
