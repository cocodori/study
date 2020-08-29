package com.coco.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.coco.domain.PageInfo;
import com.coco.domain.ReplyVO;

public interface ReplyMapper {

	int insert(ReplyVO replyVO);
	List<ReplyVO> getReplyList(@Param("bno")Long bno, @Param("pageInfo")PageInfo pageInfo);
	int update(ReplyVO replyVO);
	int delete(Long rno);
	ReplyVO getReply(Long rno);
}
