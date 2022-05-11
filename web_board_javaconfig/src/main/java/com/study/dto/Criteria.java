package com.study.dto;

import lombok.*;

@ToString
@Setter
@Getter
public class Criteria {
	private int pageNum; // 사용자가 선택한 페이지 번호
	private int amount; // 한 페이지 당 보여줄 게시물 수
	
	private String type; // 검색 조건 : T / C / W / TC / TW / TCW
	private String keyword; // 검색어
	
	public Criteria() {
		this(1, 10);
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}	
	
	// 검색 조건을 String[]으로 변경 - 검색이 아니면 빈 배열 넘기기
	// W => {"W"}, TC => {"T", "C"}, TCW => {"T", "C", 'W"}
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}
}