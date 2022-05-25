package com.study.guestbook.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;

// 페이징 결과 처리 DTO - 페이징 처리 return 값은 Page<Entity> 형태인데, Service에서는 DTO를 사용하기 때문에 Entity를 DTO로 변경하기 위한 클래스 + 화면 출력에 필요한 페이지 정보 구성

@Data
public class PageResultDTO<DTO, EN> { // EN : Entity type
	
	private List<DTO> dtoList; // 게시물 리스트 => List 타입으로 DTO 객체 보관
	
	private int page; // 현재 페이지 번호
	private int size; // 목록 사이즈
	
	private int start; // 시작 페이지 번호
	private int end; // 끝 페이지 번호
	
	private boolean prev; // 이전
	private boolean next; // 다음
	
	private List<Integer> pageList; // 페이지 번호 목록
	
	private int totalPage; // 총 페이지 번호
	
	// Page<Entity>의 Entity를 DTO로 변환하는 과정이 필요한데, 일반적인 형태는 추상클래스를 활용하는 방법이지만 매번 새로운 클래스로 추상클래스를 구현해야 하기 때문에 Function 객체로 따로 만들어 처리
	public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) { // Function<EN, DTO>는 Entity 객체를 DTO로 변환하는 함수
		dtoList = result.stream().map(fn).collect(Collectors.toList());
		totalPage = result.getTotalPages();
		makePageList(result.getPageable());
	}
	
	private void makePageList(Pageable pageable) {
		this.page = pageable.getPageNumber() + 1;
		this.size = pageable.getPageSize();
		
		int tempEnd = (int)(Math.ceil(page / 10.0)) * 10;
		
		start = tempEnd - 9;
		end = totalPage > tempEnd ? tempEnd : totalPage;
		
		prev = start > 1;
		next = totalPage > tempEnd;
		
		pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
	}
}