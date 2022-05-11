package com.study.dto;

import lombok.Data;

@Data
public class AttachDTO {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
	
	// 원본글 번호
	private int bno;
}