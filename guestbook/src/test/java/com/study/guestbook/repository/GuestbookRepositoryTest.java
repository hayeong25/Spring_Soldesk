package com.study.guestbook.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.study.guestbook.entity.Guestbook;
import com.study.guestbook.entity.QGuestbook;

@SpringBootTest
public class GuestbookRepositoryTest {
	
	@Autowired
	private GuestbookRepository repository;
	
	/* ========================== INSERT ========================== */
//	@Test
//	public void insert() {
//		// test 더미 300개 만들기
//		IntStream.rangeClosed(1, 300).forEach(i -> {
//			Guestbook guestbook = Guestbook.builder()
//											.title("Guest Title" + i)
//											.content("Guest Content" + i)
//											.writer("writer" + (i%10))
//											.build();
//			System.out.println(repository.save(guestbook));
//		});
//	}
	
	/* ========================== UPDATE ========================== */
//	@Test
//	public void update() {
//		// 600번 찾아 수정하기
//		repository.findById(600L).ifPresent(guest -> {
//			guest.setTitle("Changed Title");
//			guest.setContent("Changed Content");
//			
//			System.out.println(repository.save(guest));
//		});
//	}
	
	/* ========================== Query DSL - SEARCH ========================== */
	// 검색 - 단일항목으로 검색 / 혼합항목으로 검색
//	@Test
//	public void searchQuery() {
//		// 페이지 나누기
//		Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
//		
//		// Q domain 클래스 이용
//		QGuestbook qGuestbook = QGuestbook.guestbook;
//		
//		// title에 1이 있는 게시물 검색
//		String keyword = "1";
//		
//		// WHERE절 조건문을 넣어주는 container => WHERE title LIKE '%1%'
//		BooleanBuilder builder = new BooleanBuilder();
//		BooleanExpression expression = qGuestbook.title.contains(keyword);
//		builder.and(expression);
//		
//		// 페이지 나누기 + 생성한 WHERE절 넘기기
//		Page<Guestbook> result = repository.findAll(builder, pageable);
//		result.stream().forEach(guestbook -> {
//			System.out.println(guestbook);
//		});
//	}
	
	/* ========================== Query DSL ========================== */
	@Test
	public void searchQuery() {
		// 페이지 나누기
		Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
		
		// Q domain 클래스 이용
		QGuestbook qGuestbook = QGuestbook.guestbook;
		
		// title 혹은 content에 1이 있는 게시물 검색 & gno > 0
		String keyword = "1";
		
		// WHERE절 조건문을 넣어주는 container => WHERE gno > 0 AND (title LIKE '%1%' OR content LIKE '%1%')
		BooleanBuilder builder = new BooleanBuilder();
		BooleanExpression expTitle = qGuestbook.title.contains(keyword);
		BooleanExpression expContent = qGuestbook.content.contains(keyword);
		BooleanExpression expAll = expTitle.or(expContent);
		builder.and(expAll);
		
		builder.and(qGuestbook.gno.gt(0L)); // gno > 0
		
		// 페이지 나누기 + 생성한 WHERE절 넘기기
		Page<Guestbook> result = repository.findAll(builder, pageable);
		result.stream().forEach(guestbook -> {
			System.out.println(guestbook);
		});
	}
}