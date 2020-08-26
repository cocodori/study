package com.coco.service;

import java.util.List;

import com.coco.domain.BoardVO;
import com.coco.domain.PageInfo;

public interface BoardService {

	Long register(BoardVO boardVO);
	BoardVO getPost(Long bno);
	int modify(BoardVO boardVO);
	int remove(Long bno);
	List<BoardVO> getAllPost(PageInfo pageInfo);
	Long getTotal(PageInfo pageInfo);
	
}
