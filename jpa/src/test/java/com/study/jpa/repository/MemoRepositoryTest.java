package com.study.jpa.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.study.jpa.entity.Memo;

/*
	Spring Data JPA : 여러 종류의 인터페이스 기능을 통해서 JPA 관련 작업 처리
	
	CRUD, 페이징, 정렬을 Interface method만 호출하면 처리됨
	
	JPA Repository Interface
*/

@SpringBootTest
public class MemoRepositoryTest {
	
	@Autowired
	private MemoRepository repository;
	
	/*
		select : findById(key type), getOne(key type)
		insert : save(entity)
		update : save(entity)
		delete : deleteById(key type), delete(entity)
	*/
	
	// Test class 안에 작성하는 method는 void. 무조건 하나만 존재
//	@Test
//	public void testInsert() {
//		
//		IntStream.rangeClosed(1, 100).forEach(i -> {
////			Memo memo = new Memo();
////			memo.setMemoText("Sample..." + i);
//			
//			// @Builder 사용 시
//			Memo memo = Memo.builder().memoText("sample..." + i).build();
//			
//			// Entity Manager가 entity를 관리하면서 객체 비교 후 없으면 insert, 있으면 update
//			repository.save(memo);
//		});
//		
//	}
	
	/* ========================== read ========================== */
//	@Test
//	public void testRead() {
//		Optional<Memo> result = repository.findById(90L);
//		
//		if(result.isPresent()) {
//			System.out.println(result);
//		}
//	}
	
	/* ========================== update ========================== */
//	@Test
//	public void testUpdate() {
//		Memo memo = Memo.builder()
//						.mno(90L)
//						.memoText("update...")
//						.build();
//		System.out.println(repository.save(memo));
//	}
	
	/* ========================== delete ========================== */
//	@Test
//	public void testDelete() {
//		repository.deleteById(90L);
//	}
	
	/* ========================== paging ========================== */
//	@Test
//	public void testPaging() {
//		// 페이징 처리 : findAll(Pageable) - 페이징 처리에 필요한 정보를 전달하는 인터페이스 객체
//		Sort sort = Sort.by("mno").descending();
//		Pageable pageable = PageRequest.of(0, 10, sort);
//		Page<Memo> result = repository.findAll(pageable);
//		
//		System.out.println(result);
//		System.out.println("=============================");
//		System.out.println("총 페이지 수 : " + result.getTotalPages());
//		System.out.println("전체 게시글 수 : " + result.getTotalElements());
//		System.out.println("현재 페이지 번호 : " + result.getNumber());
//		System.out.println("페이지 당 데이터 개수 : " + result.getSize());
//		System.out.println("다음 페이지 존재 여부 : " + result.hasNext());
//		System.out.println("시작 페이지 존재 여부 : " + result.isFirst());
//		System.out.println("=============================");
//		
//		for(Memo memo : result.getContent()) {
//			System.out.println(memo);
//		}
//	}
	
	/* ========================== Query ========================== */
//	@Test
//	public void testQuery() {
//		List<Memo> list = repository.findByMnoBetweenOrderByMnoDesc(40L, 80L);
//		list.forEach(memo -> {
//			System.out.println(memo);
//		});
//	}
	
	/* ========================== Query ========================== */
	@Test
	public void testQuery() {
		// Pageable은 new 객체 생성 불가 => PageRequest 객체 사용
		Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending()); // 10개씩 가져와, mno를 기준으로 정렬할 거야
		Page<Memo> result = repository.findByMnoBetween(40L, 80L, pageable);
		result.getContent().forEach(memo -> {
			System.out.println(memo);
		});
	}
}