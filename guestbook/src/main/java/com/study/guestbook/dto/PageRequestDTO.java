package com.study.guestbook.dto;

import org.springframework.data.domain.*;
import lombok.*;

// 화면에서 전달되는 목록 데이터 DTO ex) 페이지 번호, 페이지 내 보여줄 목록 수, 검색타입, 검색어 등 담았던 Criteria DTO와 비슷

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
	
	private int page; // 현재 페이지 번호
	private int size; // 페이지 내 보여줄 목록 수
	
	private String type; // 검색 조건
	private String keyword; // 검색어
	
	public PageRequestDTO() { // 생성자에 페이지 번호 기본값과 페이지 당 보여줄 리스트 수 선언
		this.page = 1;
		this.size = 10;
	}
	
	public Pageable getPageable(Sort sort) {
		// JPA에서 Pageable 객체는 페이지 번호가 0부터 시작 => 1페이지의 경우 0이 되어야 하기 때문에 -1
		return PageRequest.of(page -1, size, sort);
	}
}