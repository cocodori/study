package com.coco.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * 댓글 페이징이 필요한 경우, 댓글 목록과 함께 전체 댓글의 수를 전달해야 한다.
 * 따라서 ReplyService는 목록과 카운트를 파라미터 인자로 받아야 한다.
 * ReplyPageDTO를 만들어서 두 가지 정보를 한 번에 받을 수 있도록 한다.
 * 
 * */
@AllArgsConstructor
@Data
public class ReplyPageDTO {
	private int replyCount;
	private List<ReplyVO> replyList;
}
