package com.study.controller;

import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.study.dto.SampleDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		logger.info("Welcome home!");
		
		return "home";
	}
	
	@GetMapping("/member/step2")
	public void step2() {
		logger.info("회원가입 요청");
	}
	
	@GetMapping("/member/changePwd")
	public void change() {
		logger.info("회원수정 오쳥");
	}
	
	@ResponseBody // JSP 찾지 말라는 의미
	@GetMapping("/test")
	public String read() {
		return "success";
	}
	
	@ResponseBody
	@GetMapping(path="/modify", produces=MediaType.APPLICATION_JSON_VALUE)
	public SampleDTO modify() {
		return new SampleDTO("1234", "park", "seoul");
	}
	
	@ResponseBody
	@GetMapping(path="/list", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SampleDTO> list() {
		return new ResponseEntity<SampleDTO>(new SampleDTO("4321", "kim", "jeju"), HttpStatus.OK);
	}
}