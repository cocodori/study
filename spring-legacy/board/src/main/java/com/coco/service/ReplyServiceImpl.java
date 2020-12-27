package com.coco.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coco.domain.PageInfo;
import com.coco.domain.ReplyPageDTO;
import com.coco.domain.ReplyVO;
import com.coco.mapper.BoardMapper;
import com.coco.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

	private final ReplyMapper replyMapper;
	private final BoardMapper boardMapper;

	@Transactional
	@Override
	public int register(ReplyVO replyVO) {
		//tbl_board테이블의 ReplyCount컬럼을 1 증가 시킨다.
		boardMapper.updateReplyCount(replyVO.getBno(), 1);
		return replyMapper.insert(replyVO);
	}

	@Override
	public ReplyPageDTO getReplyList(PageInfo pageInfo, Long bno) {
		int replyCount = replyMapper.replyCount(bno);
		List<ReplyVO> replyList = replyMapper.getReplyList(bno, pageInfo);
		
		return new ReplyPageDTO(replyCount, replyList);
	}

	@Override
	public int modify(ReplyVO replyVO) {
		return replyMapper.update(replyVO);
	}

	@Transactional
	@Override
	public int remove(Long rno) {
		ReplyVO replyVO = replyMapper.getReply(rno);
		
		//tbl_board테이블의 ReplyCount칼럼을 1 감소 시킨다.
		boardMapper.updateReplyCount(replyVO.getBno(), -1);
		
		return replyMapper.delete(rno);
	}

	@Override
	public ReplyVO getReply(Long rno) {
		return replyMapper.getReply(rno);
	}

}
