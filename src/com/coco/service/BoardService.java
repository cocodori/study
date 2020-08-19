package com.coco.service;

import java.util.List;

import com.coco.vo.BoardVO;
import com.coco.vo.PageVO;

public interface BoardService {
	List<BoardVO> getList(PageVO page);
	int register(BoardVO vo);
	BoardVO getPost(int no);
	int modify(BoardVO vo);
	int remove(int bno);
	int getTotal();
}
