package com.coco.service;

import com.coco.domain.PageInfo;
import com.coco.domain.ReplyPageDTO;
import com.coco.domain.ReplyVO;

public interface ReplyService {
	
	int register(ReplyVO replyVO);
	ReplyPageDTO getReplyList(PageInfo pageInfo, Long bno);
	int modify(ReplyVO replyVO);
	int remove(Long rno);
	ReplyVO getReply(Long rno);
	
}
