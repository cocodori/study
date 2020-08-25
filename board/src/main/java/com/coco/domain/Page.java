package com.coco.domain;

import lombok.Data;

@Data
public class Page {
	private int page;
	private int amount;
	
	public Page() {
		this(1,10);
	}
	
	public Page(int page, int amount) {
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
	
}
