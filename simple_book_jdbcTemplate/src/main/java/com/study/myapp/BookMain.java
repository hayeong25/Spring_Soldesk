package com.study.myapp;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.study.myapp.dto.BookDTO;
import com.study.myapp.service.BookService;

public class BookMain {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		BookService service = (BookService)context.getBean("service"); // BookService service = new BookServiceImpl();
		
		// 도서 정보 삽입
		// BookDTO insertDTO = new BookDTO(1500, "jQuery 입문", "유광열", 28000);
		// BookDTO insertDTO = new BookDTO(2001, "Python 데이터 분석", "유광열", 28000);
		// BookDTO insertDTO = new BookDTO(2002, "모두의 알고리즘 Python", "이승한", 28000);
		// System.out.println(service.bookInsert(insertDTO) ? "삽입 성공" : "삽입 실패");
		
		// System.out.println(service.bookUpdate(2002, 32000) ? "수정 성공" : "수정 실패");
		
		System.out.println(service.bookDelete(2002) ? "삭제 성공" : "삭제 실패");
		
		List<BookDTO> list = service.getList();
		
		for(BookDTO dto : list) {
			System.out.println(dto);
		}
	}
}