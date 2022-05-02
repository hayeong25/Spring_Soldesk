package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.study.dto.RegisterDTO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member/*")
public class RegisterController {
	
	// register.jsp 보여주는 GET 방식
	@GetMapping("/register")
	public void registerGet() {
		log.info("register.jsp form 보여주기...");
	}
	
	// register.jsp에서 POST로 들어오는 요청 Controller 생성
	@PostMapping("/register")
	public String registerPost(RegisterDTO dto) {
		log.info("login POST...." + dto.getUserid() + ", " + dto.getPassword() + ", " + dto.getPhone());
		// login 페이지 보여주기
		return "redirect:/login";
	}
}