package com.coco.service;

import java.util.List;

import com.coco.dao.BoardDAO;
import com.coco.vo.BoardVO;
import com.coco.vo.PageVO;

public class BoardServiceImpl implements BoardService {

	private BoardDAO dao;
	
	public BoardServiceImpl() {
		//초기화
		dao = new BoardDAO();
	}
	
	@Override
	public List<BoardVO> getList(PageVO page) {
		return dao.getList(page);
	}

	@Override
	public int register(BoardVO vo) {
		return dao.insert(vo);
	}

	@Override
	public BoardVO getPost(int no) {
		return dao.getPost(no);
	}

	@Override
	public int modify(BoardVO vo) {
		return dao.update(vo);
	}

	@Override
	public int remove(int bno) {
		return dao.delete(bno);
	}

	@Override
	public int getTotal() {
		return dao.getTotal();
	}
}
