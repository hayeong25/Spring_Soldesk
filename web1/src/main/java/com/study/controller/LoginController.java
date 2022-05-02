package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.study.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	@GetMapping("/login")
	public String login() {
		log.info("login...."); // WEB-INF/views/sample/login.jsp
		return "sample/login";
	}
	
//	@PostMapping("/login")
//	public void loginPost(HttpServletRequest request) {
//		String userid = request.getParameter("userid");
//		String password = request.getParameter("password");
//		String address = request.getParameter("address");
//		String age = request.getParameter("age");
//		log.info("login POST...." + userid + ", " + password + ", " + address + ", " + age);
//	}
	
	@PostMapping("/login")
	public String loginPost(@ModelAttribute("user")UserDTO userDTO) {
		log.info("login POST...." + userDTO.getUserid() + ", " + userDTO.getPassword() + ", " + userDTO.getAddress());
		// return "home"; >> Forward 방식
		// return "redirect:/calc/add"; >> GET 방식
		// return "redirect:/"; // sendRedirect 방식. 가야 할 경로 지정 (페이지 아님). Controller로 들어가게 돼 결국 home으로 이동
		// Spring은 페이지로 직접 갈 수 없고 반드시 Controller를 거쳐서 페이지를 받아가지고 와야 함 
		return "sample/logout";
	}
}