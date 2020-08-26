package com.coco.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {

	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private Long total;
	private PageInfo pageInfo;
	
	/* 게시물의 총 개수를 받아서 페이지를 몇 페이지까지 나타낼 것인지를 연산하고,
	 * 지금 머물고 있는 페이지에 '이전 페이지'가 필요한지, '다음 페이지'가 필요한지 연산한다.
	 * */
	public PageDTO(PageInfo pageInfo, Long total) {
		this.pageInfo = pageInfo;
		this.total = total;
		
		this.endPage = (int)(Math.ceil(pageInfo.getPage() / 10.0)) * 10;
		this.startPage = endPage - 9;
		
		int realEnd = (int)(Math.ceil((total*10) / pageInfo.getAmount()));

		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
}
