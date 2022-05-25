package com.study.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		log.info("index.jsp 보여주기");
		// index.jsp 보여주기
		return "index";
	}
	
}