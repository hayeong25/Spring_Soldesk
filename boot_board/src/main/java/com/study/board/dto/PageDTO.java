package com.study.board.dto;

import lombok.*;

@ToString
@Setter
@Getter
public class PageDTO {
	// 페이지 나누기 정보
	private int startPage; // 화면에서 보여지는 페이지의 시작 번호
	private int endPage; // 화면에서 보여지는 페이지의 끝 번호
	private boolean prev; // 이전
	private boolean next; // 다음
	
	// 전체 게시물 수
	private int total;
	private Criteria criteria;
	
	public PageDTO(Criteria criteria, int total) {
		this.total = total;
		this.criteria = criteria;
		
		this.endPage = (int)(Math.ceil(criteria.getPageNum() / 10.0)) * 10;
		this.startPage = this.endPage - 9;
		
		int realEnd = (int)(Math.ceil((total / 1.0) / criteria.getAmount()));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
}