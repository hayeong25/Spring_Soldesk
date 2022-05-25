package com.study.guestbook.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.study.guestbook.dto.*;
import com.study.guestbook.entity.Guestbook;
import com.study.guestbook.entity.QGuestbook;
import com.study.guestbook.repository.GuestbookRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GuestbookServiceImpl implements GuestbookService {

	@Autowired
	private GuestbookRepository repository;
	
	/* ============================ list ============================ */
	@Override
	public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
		// Sort 기준 만들기
		Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
		
		// 검색
		BooleanBuilder booleanBuilder = getSearch(requestDTO);
		
		// findAll 호출
		Page<Guestbook> result = repository.findAll(booleanBuilder, pageable);
		
		// Guestbook 타입의 매개변수를 받아 GuestbookDTO로 return
		Function<Guestbook, GuestbookDTO> fn = (entity -> entityToDto(entity));
		
		// 처리 결과인 Entity type Page 객체와 Function을 전달해서 Entity 객체를 dtoList로 변환하여 list.html 화면상에 출력할 수 있게 설정
		return new PageResultDTO<GuestbookDTO, Guestbook>(result, fn);
	}
	
	/* ============================ read ============================ */
	@Override
	public GuestbookDTO read(Long gno) {
		Optional<Guestbook> result = repository.findById(gno);
		
		// 화면에서는 DTO 사용 / 서버에서는 Entity 사용 => 변환 필요
		return result.isPresent() ? entityToDto(result.get()) : null;
	}
	
	/* ============================ register ============================ */
	@Override
	public Long register(GuestbookDTO insertDTO) {
		log.info("========== service register : " + insertDTO + " ========== ");
		
		// DTO -> Service -> Entity (DB 작업) -> DTO -> Service
		// Entity 변환
		Guestbook entity = dtoToEntity(insertDTO);
		
		log.info("========== Entity : " + entity + " ========== ");
		
		// DB 작업
		repository.save(entity);
		
		return entity.getGno();
	}
	
	/* ============================ modify ============================ */
	@Override
	public void modify(GuestbookDTO updateDTO) {
		log.info("========== modify ========== ");
		Optional<Guestbook> result = repository.findById(updateDTO.getGno());
		if(result.isPresent()) {
			Guestbook entity = result.get();
			entity.setTitle(updateDTO.getTitle());
			entity.setContent(updateDTO.getContent());
			
			repository.save(entity);
		}
	}

	/* ============================ remove ============================ */
	@Override
	public void remove(Long gno) {
		log.info("========== remove ========== ");
		repository.deleteById(gno);
	}

	/* ============================ search ============================ */
	private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
		String type = requestDTO.getType(); // 검색 조건 가져오기
		String keyword = requestDTO.getKeyword(); // 검색어 가져오기
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QGuestbook qGuestbook = QGuestbook.guestbook;
		
		BooleanExpression expression = qGuestbook.gno.gt(0L);
		booleanBuilder.and(expression);
		
		if(type == null || type.trim().length() == 0) {
			return booleanBuilder;
		}
		
		BooleanBuilder conditionBuilder = new BooleanBuilder();
		
		if(type.contains("t")) {
			conditionBuilder.or(qGuestbook.title.contains(keyword));
		}
		if(type.contains("c")) {
			conditionBuilder.or(qGuestbook.content.contains(keyword));
		}
		if(type.contains("w")) {
			conditionBuilder.or(qGuestbook.writer.contains(keyword));
		}
		
		booleanBuilder.and(conditionBuilder);
		
		return booleanBuilder;
	}
}