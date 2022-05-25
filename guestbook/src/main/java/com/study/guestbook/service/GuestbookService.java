package com.study.guestbook.service;

import com.study.guestbook.dto.GuestbookDTO;
import com.study.guestbook.dto.PageRequestDTO;
import com.study.guestbook.dto.PageResultDTO;
import com.study.guestbook.entity.Guestbook;

public interface GuestbookService {

	// 등록
	Long register(GuestbookDTO insertDTO);
	
	// 목록
	PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);
	
	// 조회
	GuestbookDTO read(Long gno);
	
	// 수정
	void modify(GuestbookDTO updateDTO);
	
	// 삭제
	void remove(Long gno);
	
	// Entity(Guestbook) --> DTO(GuestbookDTO) 변환
	default GuestbookDTO entityToDto(Guestbook entity) {
		GuestbookDTO dto = GuestbookDTO.builder()
										.gno(entity.getGno())
										.title(entity.getTitle())
										.content(entity.getContent())
										.writer(entity.getWriter())
										.regDate(entity.getRegDate())
										.updateDate(entity.getUpdateDate())
										.build();
		return dto;
	}
	
	// Service에서 객체 넘길 때 Entity로 넘겨줘야 하기 때문에 DTO에서 Entity로 객체 변환하는 작업 필요 (처음부터 DTO 대신 모두 Entity 쓰는 것도 가능)
	// DTO(GuestbookDTO) --> Entity(Guestbook) 변환
	default Guestbook dtoToEntity(GuestbookDTO dto) {
		Guestbook entity = Guestbook.builder()
									.title(dto.getTitle())
									.content(dto.getContent())
									.writer(dto.getWriter())
									.build();
		return entity;
	}
	
}