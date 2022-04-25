package com.study.myapp.dto;

// DTO는 Spring이 관리하지 않음

import lombok.*;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
	private int code;
	private String title;
	private String writer;
	private int price;
}