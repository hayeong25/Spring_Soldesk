package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board/*")
public class BoardController {
	@GetMapping("/insert")
	public void insert() {
		log.info("insert...");
	}
	
	@GetMapping("/modify")
	public void modify() {
		log.info("modify...");
	}
	
	@GetMapping("/read")
	public void read() {
		log.info("read...");
	}
	
	@GetMapping("/list")
	public void list() {
		log.info("list...");
	}
}