package com.coco.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class PageInfo {
	private int page;
	private int amount;
	
	private String type;
	private String keyword;
	
	public PageInfo() {
		this(1,10);
	}
	
	public PageInfo(int page, int amount) {
		if(page < 0 || amount <= 0) {	//유효성 검사
			this.page = 0;
			this.amount = 10;
			return;
		}
		
		this.page = page;
		this.amount = amount;
	}
	
	public int getSkip() {
		return (this.page-1) * this.amount;
	}
	
	public String[] getTypeArr() {
		System.out.println("type : " + type);
		return type == null || type.length() == 0
					? new String[] {} : type.split("");
	}
	
	public String getUrlList () {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("page", this.getPage())
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());
		
		return builder.toUriString();
	}
	
}
