package com.coco.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class PageOper {
	private final static Logger log = Logger.getGlobal();
	private int startPage, endPage;
	private boolean prev, next;
	private int total;
	private PageVO pageVO;
	
	public PageOper(PageVO vo, int total) {
		this.pageVO = vo;
		this.total = total;
		
		this.endPage = (int)(Math.ceil(pageVO.getPage() / 10.0)) * 10;
		this.startPage = this.endPage - 9;
		
		int realEnd = (int)(Math.ceil((total * 1.0)/pageVO.getAmount()));
	
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
		
		log.info("startPage : " + startPage);
		log.info("endPage : " + endPage);
		
		log.info("realEnd : " + realEnd);
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getTotal() {
		return total;
	}

	public PageVO getPageVO() {
		return pageVO;
	}
	
}
