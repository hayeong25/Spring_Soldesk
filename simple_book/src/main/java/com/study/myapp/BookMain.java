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
		BookDTO insertDTO = new BookDTO(1500, "jQuery 입문", "유광열", 28000);
		System.out.println(service.bookInsert(insertDTO) ? "삽입 성공" : "삽입 실패");
		
		List<BookDTO> list = service.getList();
		
		for(BookDTO dto : list) {
			System.out.println(dto);
		}
	}
}