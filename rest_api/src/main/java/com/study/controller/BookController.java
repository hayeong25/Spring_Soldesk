package com.study.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.study.dto.BookDTO;
import com.study.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/book/*")
public class BookController {
	
	@Autowired
	private BookService service;
	
	/* -------------------------- INDEX -------------------------- */
	@GetMapping("/index")
	public String insert() {
		return "/book/book_test";
	}
	
	/* -------------------------- 도서 목록 -------------------------- */
	@GetMapping(path="/list", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BookDTO>> list() {
		List<BookDTO> list = service.getList();
		return new ResponseEntity<List<BookDTO>>(list, HttpStatus.OK);
	}
	
	/* -------------------------- 도서 조회 -------------------------- */
	@GetMapping(path="/{code}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookDTO> get(@PathVariable("code")int code) {
		return new ResponseEntity<BookDTO>(service.getRow(code), HttpStatus.OK);
	}
	
	/* -------------------------- 도서 삽입 -------------------------- */
	// JSON으로 받을 경우 contentType:'application/json' & @RequestBody 필수
	@PostMapping("/new1")
	public ResponseEntity<String> insert(@RequestBody BookDTO insertDTO) {
		return service.bookInsert(insertDTO) ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	// 비동기식으로 클라이언트로부터 데이터를 가져올 때 객체에 담고자 한다면 $("form").serialize()
	@PostMapping("/new2")
	public ResponseEntity<String> insert2(BookDTO insertDTO) {
		return service.bookInsert(insertDTO) ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	/* -------------------------- 도서 수정 -------------------------- */
	@PutMapping(path="/{code}")
	public ResponseEntity<String> update(@PathVariable("code")int code, @RequestBody BookDTO dto) {
		return service.bookUpdate(code, dto.getPrice()) ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	/* -------------------------- 도서 삭제 -------------------------- */
	@DeleteMapping(path="/{code}")
	public ResponseEntity<String> delete(@PathVariable("code")int code) {
		return service.bookDelete(code) ? new ResponseEntity<String>("success", HttpStatus.OK) : new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
}