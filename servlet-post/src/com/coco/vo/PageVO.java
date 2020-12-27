package com.coco.vo;

public class PageVO {
	private Integer page, amount;
	
	public PageVO() {
		this(0,10);
	}

	public PageVO(int page, int amount) {
		this.page = page;
		this.amount = amount;
	}
	
	//사실상 호출하는 메서드는 이것이다.
	public int getSkip() {
		return (page-1)*10;
	}

	public int getPage() {
		return page;
	}

	public int getAmount() {
		return amount;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
