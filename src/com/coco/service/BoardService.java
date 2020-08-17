package com.coco.service;

import java.util.List;

import com.coco.vo.BoardVO;

public interface BoardService {
	List<BoardVO> getList();
	int register(BoardVO vo);
	BoardVO getPost(int no);
	
}
