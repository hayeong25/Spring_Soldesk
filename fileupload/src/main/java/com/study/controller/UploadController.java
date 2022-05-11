package com.study.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UploadController {
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload form 요청");
	}
	
	// type=file에 들어있는 값 가져오기
	@PostMapping("/uploadForm")
	public void uploadFormPost(MultipartFile[] uploadFile) {
		log.info("upload 요청");
		
		// 업로드 폴더 지정
		String uploadPath = "C:\\Users\\hayeo\\upload";
		
		for(MultipartFile f : uploadFile) {
			log.info("파일명 : " + f.getOriginalFilename());
			log.info("파일 크기 : " + f.getSize());
			
			try {
				File save = new File(uploadPath, f.getOriginalFilename());
				// 파일 저장
				f.transferTo(save);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 파일 다운로드 (상태코드도 같이 보내기)
//	@GetMapping(path="/download", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
//	public ResponseEntity<Resource> downloadFile(String fileName) {
//		log.info("download 요청");
//		Resource resource = new FileSystemResource("C:\\Users\\hayeo\\upload" + fileName);
//		String resourceName = resource.getFilename();
//		
//		HttpHeaders headers = new HttpHeaders();
//		
//		try {
//			headers.add("Content-Disposition", "attachment;filename=" + new String(resourceName.getBytes("UTF-8"), "ISO-8859-1"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		
//		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
//	}
}