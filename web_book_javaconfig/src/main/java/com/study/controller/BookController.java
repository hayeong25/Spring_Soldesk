package com.study.controller;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.study.dto.BookDTO;
import com.study.service.BookService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/book/*")
public class BookController {
	@Autowired
	private BookService service;
	
	/* ------------------------- 도서 목록 -------------------------*/
	// list.jsp
	@GetMapping("/list")
	public void listGet(Model model) { // Spring에서는 request.setAttribute가 아닌 Model 사용
		log.info("도서 전체 목록 보여주기");
		// service 호출
		List<BookDTO> list = service.list();
		model.addAttribute("list", list);
	}
	
	/* ------------------------- 도서 입력 -------------------------*/
	// insert.jsp
	@GetMapping("/insert")
	public void insertGet() {
		log.info("도서 입력 form 보여주기");
	}
	
	// 도서 입력 요청
	@PostMapping("/insert")
	public String insertPost(BookDTO insertDTO, RedirectAttributes rttr) {
		log.info("도서 입력 요청");
		try {
			if(service.insert(insertDTO)) {
				return "redirect:/book/list";
			}
		} catch (Exception e) {
			rttr.addFlashAttribute("error", "도서 코드를 확인하세요");
			return "/book/insert";
		}
		return "/book/insert";
	}
	
	/* ------------------------- 도서 검색 -------------------------*/
	// search.jsp
	@GetMapping("/search")
	public void searchGet() {
		log.info("도서 검색 form 보여주기");
	}
	
	@PostMapping("/search")
	public String searchPost(String criteria, String keyword, Model model) {
		log.info("도서 검색 요청");
		List<BookDTO> list = service.search(criteria, keyword);
		model.addAttribute("list", list);
		return "/book/list";
		//return "redirect:/book/list";  // 컨트롤러 get(/book/list) 가기 
	}
	
	/* ------------------------- 도서 수정 -------------------------*/
	// update.jsp
	@GetMapping("/update")
	public void updateGet() {
		log.info("도서 수정 form 보여주기");
	}
	
	@PostMapping("/update")
	public String updatePost(@Param("code")int code, @Param("price")int price) {
		log.info("도서 수정 요청");
		if(service.update(code, price)) {
			return "redirect:/book/list";
		}
		return "/book/update";
	}
	
	/* ------------------------- 도서 삭제 -------------------------*/
	// delete.jsp
	@GetMapping("/delete")
	public void deleteGet() {
		log.info("도서 삭제 form 보여주기");
	}
	
	// 도서 삭제 요청
	@PostMapping("/delete")
	public String deletePost(int code) {
		log.info("도서 삭제 요청");
		if(service.delete(code)) {
			return "redirect:/book/list";
		}
		return "/book/delete";
	}
}