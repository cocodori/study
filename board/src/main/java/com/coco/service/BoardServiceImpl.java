package com.coco.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coco.domain.BoardVO;
import com.coco.domain.PageInfo;
import com.coco.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@RequiredArgsConstructor
@Log4j
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;
	
	@Override
	public Long register(BoardVO boardVO) {
		/* 데이터베이스에 데이터를 추가하는 데 성공하면 
		 * 추가된 데이터의 bno를 반환하고, 실패하면 0을 반환한다.
		 * */
		Long result =
				boardMapper.insert(boardVO) == 1 ?
						boardMapper.lastInsertId() : 0;
		
		return result;
	}

	@Override
	public List<BoardVO> getAllPost(PageInfo page) {
		
		PageInfo pageInfo = page.getPage() < 0 || page.getAmount() < 0 ?
				new PageInfo() : page;
				
		return boardMapper.getAllPost(pageInfo);
	}

	@Override
	public BoardVO getPost(Long bno) {
		return boardMapper.getPost(bno);
	}

	@Override
	public int modify(BoardVO boardVO) {
		return boardMapper.update(boardVO);
	}

	@Override
	public int remove(Long bno) {
		
		int result = boardMapper.delete(bno);
		log.info(result == 0 ? "존재하지 않는 게시물" : "삭제되었습니다.");
		
		return result;
	}

	@Override
	public Long getTotal() {
		return boardMapper.getTotal();
	}

}
