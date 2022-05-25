package com.study.guestbook.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Getter;

/*
	Entity 작업 시 데이터의 등록 시간과 수정 시간과 같이 자동으로 추가되고 변경되어야 하는 컬럼이 존재함
	시간 입력 - 등록, 수정 시간 => 프로그램 내에서 처리하는 것보다 자동으로 처리가 되도록 설정
	
	@MappedSuperclass : 테이블로 생성하지 말 것
	
	@CreatedDate : Entity 생성 시간
	@LastModifiedDate : Entity 최종 수정 시간
	=> updatable=false로 지정하면 entity 변화가 일어났을 때 column을 업데이트 하지 않겠다는 의미
	
	@EntityListeners(AuditingEntityListener.class) : Entity Manager가 관리하는 Persistence Context 변화를 감지하는 Listener
*/

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public class BaseEntity {
	
	@CreatedDate
	@Column(name="regdate", updatable = false)
	private LocalDateTime regDate;
	
	@LastModifiedDate
	@Column(name="updatedate")
	private LocalDateTime updateDate;
	
}