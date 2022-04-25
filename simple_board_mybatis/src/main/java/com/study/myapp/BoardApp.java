package com.study.myapp;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.study.myapp.dto.BoardDTO;
import com.study.myapp.service.BoardService;

public class BoardApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		BoardService service = (BoardService)context.getBean("service");
		
		// 게시글 등록
//		BoardDTO insertDTO = new BoardDTO();
//		insertDTO.setTitle("Spring 게시판3");
//		insertDTO.setContent("Spring 게시판 작성하기3");
//		insertDTO.setWriter("꼬물이");
//		System.out.println(service.boardInsert(insertDTO)? "삽입 성공" : "삽입 실패");
		
		// 게시글 전체 목록 가져오기
		List<BoardDTO> list = service.getList();
		for(BoardDTO dto : list) {
			System.out.println(dto);
		}
		
		// 게시글 하나 가져오기
//		System.out.println(service.getRow(1));
		
		// 게시글 제목, 내용 업데이트
		BoardDTO updateDTO = new BoardDTO();
		updateDTO.setBno(3);
		updateDTO.setTitle("Spring 게시판 수정");
		updateDTO.setContent("Spring 게시판 수정하기");
		System.out.println(service.boardUpdate(updateDTO) ? "수정 성공" : "수정 실패");
		
		// 게시글 삭제
		System.out.println(service.boardDelete(2) ? "삭제 성공" : "삭제 실패");
	}
}