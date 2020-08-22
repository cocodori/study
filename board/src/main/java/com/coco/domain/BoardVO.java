package com.coco.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardVO {
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date moddate;
}
