package com.coco.service;

import java.util.List;

import com.coco.domain.PageInfo;
import com.coco.domain.ReplyVO;

public interface ReplyService {
	
	int register(ReplyVO replyVO);
	List<ReplyVO> getReplyList(Long bno, PageInfo pageInfo);
	int modify(ReplyVO replyVO);
	int remove(Long rno);
	
}
