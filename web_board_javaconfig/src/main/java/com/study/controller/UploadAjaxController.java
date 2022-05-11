package com.study.controller;

import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.study.dto.AttachDTO;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Slf4j
@Controller
public class UploadAjaxController {
	
	@GetMapping("/uploadAjax")
	public String uploadAjaxForm() {
		log.info("ajax upload form 요청");
		return "uploadForm_ajax";
	}
	
	@PostMapping("/uploadAjax")
	public ResponseEntity<List<AttachDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("ajax upload 요청");
		
		List<AttachDTO> attachList = new ArrayList<AttachDTO>();
		
		// 업로드 기본 폴더 지정
		String uploadBasicPath = "C:\\Users\\hayeo\\upload";
		
		// 업로드 세부 폴더 지정
		String uploadFolderPath = getFolder();
		
		// 전체 업로드 경로 생성
		File uploadPath = new File(uploadBasicPath, uploadFolderPath);
		
		if(!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		
		// 업로드 파일명
		String uploadFileName = "";
		File save = null;
		
		for(MultipartFile f : uploadFile) {
			log.info("파일명 : " + f.getOriginalFilename());
			log.info("파일 크기 : " + f.getSize());
			
			// 파일명 가져오기
			String oriFileName = f.getOriginalFilename();
			
			// 중복 파일명 해결하기
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + oriFileName;

			// 업로드 파일 객체 생성
			AttachDTO attachDTO = new AttachDTO();
			attachDTO.setUploadPath(uploadFolderPath);
			attachDTO.setFileName(oriFileName);
			attachDTO.setUuid(uuid.toString());
			
			save = new File(uploadPath, uploadFileName);
			try {
				if(chechImageType(save)) {
					attachDTO.setFileType(true);
					
					// 이미지라면 썸네일 저장 - 원본 크기와 80x80 크기 2번 저장
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					InputStream in = f.getInputStream();
					Thumbnailator.createThumbnail(in, thumbnail, 80, 80);
					in.close();
					thumbnail.close();
				}
				
				// 파일 저장
				f.transferTo(save);
				
				attachList.add(attachDTO);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ResponseEntity<List<AttachDTO>>(attachList, HttpStatus.OK);
	}
	
	// 썸네일 이미지 보여주기 - display Controller
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName) {
		log.info("썸네일 요청");
		File file = new File("c:\\Users\\hayeo\\upload\\" + fileName);
		
		ResponseEntity<byte[]> image = null;
		
		HttpHeaders header = new HttpHeaders();
		
		try {
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			image = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	@GetMapping(path="/download", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(String fileName) {
		log.info("download 요청");
		Resource resource = new FileSystemResource("C:\\Users\\hayeo\\upload\\" + fileName);
		String resourceUIDName = resource.getFilename();
		// uuid 값 제거
		String resourceName = resourceUIDName.substring(resourceUIDName.indexOf("_")+1);
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.add("Content-Disposition", "attachment;filename=" + new String(resourceName.getBytes("UTF-8"), "ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	// 서버 파일 삭제
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName, String type) {
		log.info("파일 삭제 요청");
		// 이미지 파일인 경우 썸네일 경로가, 아닌 경우는 원본 경로 그대로 넘어옴
		try {
			File file = new File("c:\\Users\\hayeo\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
			file.delete(); // 이미지인 경우 썸네일 삭제, txt 파일은 원본 파일 삭제
			
			// 이미지인 경우 원본 파일 삭제 작업 필요
			if(type.equals("image")) {
				// 원본 파일 경로
				String largeName = file.getAbsolutePath().replace("s_", "");
				file = new File(largeName);
				file.delete();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	// 이미지 파일 여부 확인
	private boolean chechImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 폴더 생성 메소드 - 일자별로 폴더 생성해 파일 다운로드
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
	}
}