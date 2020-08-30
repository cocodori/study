package com.coco.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coco.domain.PageInfo;
import com.coco.domain.ReplyPageDTO;
import com.coco.domain.ReplyVO;
import com.coco.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

	private final ReplyMapper replyMapper;
	
	@Override
	public int register(ReplyVO replyVO) {
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

	@Override
	public int remove(Long rno) {
		return replyMapper.delete(rno);
	}

	@Override
	public ReplyVO getReply(Long rno) {
		return replyMapper.getReply(rno);
	}

}
