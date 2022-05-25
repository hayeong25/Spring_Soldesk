package com.study.jpa.entity;

import javax.persistence.*;
import lombok.*;

/*
	DB Entity
	- DB 상에서 데이터로 관리하는 대상
	- 상품, 회사, 직원 등과 같이 명사이면서 업무와 관련된 데이터
	- 테이블
	
	@Table : @Entity와 같이 사용. Entity 클래스를 어떤 테이블로 생성할 것인지? => sql에서 직접 CREATE TABLE ~ 할 필요 없이 테이블 생성
		cf) @Entity가 붙은 클래스는 primary key에 해당하는 특정 필드를 @Id로 지정해야 함
	
	GenerationType
	- SEQUENCE : Oracle + @SequenceGenerator 같이 사용
	- INDENTITY : 사용하는 DB(MySQL, MariaDB)가 key 생성 결정
	- AUTO : default. 특정 DB(MySQL, MariDB)에 맞게 자동 생성
	- TABLE : key 생성 전용 테이블 사용하겠다는 의미. @TableGenerator와 함께 사용
	
	@SequenceGenerator
	- name=SequenceGenerator명 (필수)
	- sequenceName=Sequence명 (옵션)
	- allocationSize = 증가값
	
	@Column
	- length
	- nullable
*/

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "memotbl") // memotbl 테이블과 연결되어 있음을 알림
@SequenceGenerator(name = "mem_seq_gen", sequenceName = "mem_seq", allocationSize = 1)
@Entity // 클래스가 Entity임을 명시하면 Entity Manager가 관리
public class Memo {
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "") // 어떤 식으로 primary key 값을 생성해서 집어넣을지
	@Id // mno는 primary key
	private Long mno;
	
	@Column(length = 200, nullable = false)
	private String memoText;
	
}