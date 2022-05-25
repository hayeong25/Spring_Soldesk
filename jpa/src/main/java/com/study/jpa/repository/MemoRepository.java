package com.study.jpa.repository;

import java.util.List;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.study.jpa.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> { // Entity명, Id 타입
	// 사용할 수 있는 여러 개의 메소드들이 제공됨
	
	// 새로운 메소드 생성도 가능함

	// mno를 기준으로 between 구문 사용하고 order by 적용 => 여러 행 조회
	// ex) SELECT * FROM memotbl WHERE mno BETWEEN 10 AND 20 ORDER BY mno DESC;
	List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);
	Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable); // Pageable 사용 시 무조건 return 타입은 Page
	
	// 메소드 생성 - 위처럼 find~ 안 하고 @Query 사용해 직접 쿼리문을 날려도 됨
//	@Query("select mno from memo order by mno desc")
//	List<Memo> getListDesc();
	
//	@Query("update memo set memoText =:memoText where mno =:mno")
//	int updateMemoText(@Param("mno")Long mno, @Param("memoText")String memoText);
	
	// Native SQL : 원래의 SQL 구문 그대로 사용하기
//	@Query(value="select mno from memo order by mno desc", nativeQuery = true)
//	List<Memo> getNativeListDesc();
}