package com.coco.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coco.domain.BoardAttachVO;
import com.coco.domain.BoardVO;
import com.coco.domain.PageInfo;
import com.coco.mapper.BoardAttachMapper;
import com.coco.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@RequiredArgsConstructor
@Log4j
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;
	private final BoardAttachMapper boardAttachMapper;
	
	@Transactional
	@Override
	public Long register(BoardVO boardVO) {
		/* 데이터베이스에 데이터를 추가하는 데 성공하면 
		 * 추가된 데이터의 bno를 반환하고, 실패하면 0을 반환한다.
		 * */
		Long result =
				boardMapper.insert(boardVO) == 1 ?
						boardMapper.lastInsertId() : 0;
						
		if(boardVO.getAttachList() == null || boardVO.getAttachList().size()<=0) {
			return result;
		}
		
		//첨부파일이 있으면 첨부파일을 DB에 저장.
		boardVO.getAttachList().forEach(attach -> {
			attach.setBno(boardMapper.lastInsertId());
			boardAttachMapper.insert(attach);
		});
		
		return result;
	}

	@Override
	public List<BoardVO> getAllPost(PageInfo page) {
		log.info("page : " + page);
		PageInfo pageInfo = page.getPage() < 0 || page.getAmount() < 0 ?
				new PageInfo() : page;
				
		return boardMapper.getAllPost(pageInfo);
	}

	@Override
	public BoardVO getPost(Long bno) {
		return boardMapper.getPost(bno);
	}

	@Transactional
	@Override
	public int modify(BoardVO boardVO) {
		log.info(boardVO);
		
		//게시물 수정
		int modifyResult = boardMapper.update(boardVO);
		
		//기존 파일을 모두 지운다.
		boardAttachMapper.deleteAll(boardVO.getBno());
		
		//첨부파일 등록
		if (modifyResult == 1 && boardVO.getAttachList() != null
			&& boardVO.getAttachList().size() > 0) {

			boardVO.getAttachList().forEach(attach -> {
				attach.setBno(boardVO.getBno());
				boardAttachMapper.insert(attach);
			});
		}
		
		return modifyResult;
	}

	@Transactional
	@Override
	public int remove(Long bno) {
		boardAttachMapper.deleteAll(bno);
		return boardMapper.delete(bno);
	}

	@Override
	public Long getTotal(PageInfo pageInfo) {
		
		log.info("pageINfo : " +pageInfo);
		
		return boardMapper.getTotal(pageInfo);
	}

	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {
		
		log.info("get Attach List by Bno :" + bno);
		
		return boardAttachMapper.findByBno(bno);
	}

}
