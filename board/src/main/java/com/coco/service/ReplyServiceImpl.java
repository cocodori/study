package com.coco.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coco.domain.PageInfo;
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
	public List<ReplyVO> getReplyList(Long bno, PageInfo pageInfo) {
		return replyMapper.getReplyList(bno, pageInfo);
	}

	@Override
	public int modify(ReplyVO replyVO) {
		return replyMapper.update(replyVO);
	}

	@Override
	public int remove(Long rno) {
		return replyMapper.delete(rno);
	}

}
