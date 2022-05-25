package com.study.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.study.guestbook.entity.Guestbook;

// view(HTML) - controller - service - guestbookRepository - 테스트

public interface GuestbookRepository extends JpaRepository<Guestbook, Long>, QuerydslPredicateExecutor<Guestbook> {
	// CRUD 작업, 정렬, page 메소드 가지고 있음
	
	// Spring이 자동으로 관리
	
}