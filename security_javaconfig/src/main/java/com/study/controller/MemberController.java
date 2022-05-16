package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.study.dto.SpUser;
import com.study.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/register")
	public void registerGet(SpUser user) {
		log.info("회원가입 form 요청");
	}
	
	@PostMapping("/register")
	public String registerPost(SpUser user) {
		log.info("회원가입 요청");
		service.register(user);
		return "redirect:/login";
	}
}