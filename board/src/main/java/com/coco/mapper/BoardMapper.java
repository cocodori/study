package com.coco.mapper;

import java.util.List;

import com.coco.domain.BoardVO;

public interface BoardMapper {
	int insert(BoardVO boardVO);
	List<BoardVO> getAllPost();
	BoardVO getPost(Long bno);
	int update(BoardVO boardVO);
	int delete(Long bno);
}
