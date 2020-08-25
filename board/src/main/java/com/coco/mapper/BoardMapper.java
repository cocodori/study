package com.coco.mapper;

import java.util.List;

import com.coco.domain.BoardVO;
import com.coco.domain.Page;

public interface BoardMapper {
	int insert(BoardVO boardVO);
	List<BoardVO> getAllPost(Page page);
	BoardVO getPost(Long bno);
	int update(BoardVO boardVO);
	int delete(Long bno);
	Long lastInsertId();
	
	Long getTotal();

}
