package com.coco.mapper;

import java.util.List;

import com.coco.domain.BoardVO;
import com.coco.domain.PageInfo;

public interface BoardMapper {
	int insert(BoardVO boardVO);
	List<BoardVO> getAllPost(PageInfo page);
	BoardVO getPost(Long bno);
	int update(BoardVO boardVO);
	int delete(Long bno);
	Long lastInsertId();
	
	Long getTotal(PageInfo pageInfo);

}
