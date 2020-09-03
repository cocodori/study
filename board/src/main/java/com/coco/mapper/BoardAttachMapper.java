package com.coco.mapper;

import java.util.List;

import com.coco.domain.BoardAttachVO;

public interface BoardAttachMapper {
	public int insert(BoardAttachVO boardAttachVO);
	public int delete(String uuid);
	public List<BoardAttachVO> findByBno(Long bno);
}
