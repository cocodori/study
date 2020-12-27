package com.coco.domain;

import lombok.Data;

/* 첨부 파일 정보를 저장하는 클래스*/
@Data
public class AttachFileDTO {
	private String fileName;	//원본 파일명
	private String uploadPath;	//업로드된 파일의 경로
	private String uuid;		//UUID값
	private boolean image;		//image파일인가?
}
