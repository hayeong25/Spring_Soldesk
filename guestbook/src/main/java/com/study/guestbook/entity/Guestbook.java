package com.study.guestbook.entity;

import javax.persistence.*;
import lombok.*;

/*
	CREATE TABLE guestbook (
		gno NUMBER(20) NOT NULL,
		title VARCHAR2(100) NOT NULL,
		content VARCHAR2(1500) NOT NULL,
		writer VARCHAR2(50) NOT NULL,
		regdate TIMESTAMP NOT NULL,
		updatedate TIMESTAMP NOT NULL,
		PRIMARY KEY(gno)
	);
	
	CREATE SEQUENCE guest_seq;
*/

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name="guestbook")
public class Guestbook extends BaseEntity{
	
	@SequenceGenerator(name = "guest_seq_gen", allocationSize = 1, sequenceName = "guest_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guest_seq_gen")
	@Id
	private Long gno;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	@Column(length = 1500, nullable = false)
	private String content;
	
	@Column(length = 50, nullable = false)
	private String writer;
}