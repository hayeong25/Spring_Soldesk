package com.study.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.study.dto.AttachDTO;
import com.study.mapper.AttachMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileCheckTask {
	@Autowired
	private AttachMapper mapper;
	
	// 전일자 폴더 구하기
	private String getFolderYesterDay() {
		// 어제 날짜 추출
		LocalDate yesterday = LocalDate.now().minusDays(1);
		
		// 추출된 날짜 포맷 변경
		String str = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		return str.replace("-", File.separator); // "2022-05-09" => "2022\05\09"
	}
	
	@Scheduled(cron="0 0 2 * * *") // 새벽 2시
	public void checkFiles() {
		log.info("file check task run...");
		// 어제 날짜의 첨부파일 목록 가져오기 (DB)
		List<AttachDTO> oldList = mapper.getOldFiles();
		List<Path> fileListPaths = oldList.stream().map(dto -> Paths.get("c:\\Users\\hayeo\\upload", dto.getUploadPath(), dto.getUuid() + "_" + dto.getFileName())).collect(Collectors.toList());
		oldList.stream().filter(dto -> dto.isFileType() == true).map(dto -> Paths.get("c:\\Users\\hayeo\\upload", dto.getUploadPath(), "s_" + dto.getUuid() + "_" + dto.getFileName())).forEach(f -> fileListPaths.add(f));
		
		// 전일자 폴더의 파일 목록 추출
		File targetDir = Paths.get("c:\\Users\\hayeo\\upload", getFolderYesterDay()).toFile();
		File[] removeFiles = targetDir.listFiles(f -> fileListPaths.contains(f.toPath()) == false);
		
		// 비교 후 폴더 안의 파일 삭제
		for(File remove : removeFiles) {
			log.info("삭제 파일 : " + remove.getAbsolutePath());
			remove.delete();
		}
	}
}