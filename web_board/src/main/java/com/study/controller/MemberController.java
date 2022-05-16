package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@GetMapping("/login")
	public void login() {
		log.info("login form 요청");
	}
	
	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", "아이디 또는 비밀번호를 확인해 주세요.");
		return "login";
	}
	
	@GetMapping("/admin-page")
	public String adminPage() {
		log.info("admin page 요청");
		return "adminpage";
	}
}