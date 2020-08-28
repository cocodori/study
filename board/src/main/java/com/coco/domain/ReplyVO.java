package com.coco.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReplyVO {
	private Long rno;
	private Long bno;
	private String reply;
	private String replyer;
	private String replyDate;
	private String moddate;
}
