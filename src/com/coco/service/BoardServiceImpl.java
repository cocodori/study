package com.coco.service;

import java.util.List;

import com.coco.dao.BoardDAO;
import com.coco.vo.BoardVO;

public class BoardServiceImpl implements BoardService {

	private BoardDAO dao;
	
	public BoardServiceImpl() {
		//초기화
		dao = new BoardDAO();
	}
	
	@Override
	public List<BoardVO> getList() {
		return dao.getList();
	}

	@Override
	public int register(BoardVO vo) {
		return dao.insert(vo);
	}

	@Override
	public BoardVO getPost(int no) {
		return dao.getPost(no);
	}
}
