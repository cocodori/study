package com.coco.service;

import java.util.List;

import com.coco.domain.BoardVO;

public interface BoardService {

	Long register(BoardVO boardVO);
	List<BoardVO> getAllPost();
	BoardVO getPost(Long bno);
	int modify(BoardVO boardVO);
	int remove(Long bno);
	
}
