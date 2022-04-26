package com.study.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.dto.UserDTO;

import lombok.extern.slf4j.Slf4j;

@Controller // Spring Framework가 관리하는 bean이라는 것을 알림
@Slf4j
@RequestMapping("/sample/*") // http://localhost:9090/sample/**
public class SampleController {
	
	/*
		Controller의 return 타입이 void인 경우
		http://localhost:9090/ 다음 부분이 JSP 페이지를 찾는 데 사용됨
		
		Controller의 return 타입이 String인 경우
		return 값만 가지고 JSP 페이지를 찾음
	*/
	
	// @RequestMapping(path="", method=RequestMethod.GET) == @GetMapping
	@GetMapping("/basic") // http://localhost:9090/sample/basic
	public void basic() {
		log.info("basic...."); // WEB-INF/views/sample/basic
	}
	
	// GET인지 POST인지 설정하지 않으면 둘 다 허용
	@RequestMapping(path="/login", method=RequestMethod.GET) // http://localhost:9090/sample/login
	public void login() {
		log.info("login...."); // WEB-INF/views/sample/login.jsp
	}
	
	/*
		POST 방식 (Controller parameter 수집) 방법
		1) 변수 사용 => GET 방식에서도 가능
		2) DTO 객체 사용
		3) HttpServletRequest 객체 사용
	*/
//	@RequestMapping(path="/login", method=RequestMethod.POST)
//	public void loginPost(String userid, String password, String address) {
//		log.info("login POST...." + userid + ", " + password + ", " + address);
//	}
	
//	@PostMapping("/login")
//	public void loginPost(UserDTO userDTO) {
//		log.info("login POST...." + userDTO.getUserid() + ", " + userDTO.getPassword() + ", " + userDTO.getAddress());
//	}
	
	@PostMapping("/login")
	public void loginPost(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String age = request.getParameter("age");
		log.info("login POST...." + userid + ", " + password + ", " + address + ", " + age);
	}
	
	@RequestMapping(path="/doA", method=RequestMethod.GET) // WEB-INF/views/sample/doA
	public void doA() {
		log.info("doA...."); // WEB-INF/views/sample/doA.jsp
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET) // WEB-INF/views/insert
	public String insert() {
		log.info("insert..."); // WEB-INF/views/insert.jsp
		return "insert"; // void가 아닌 경우, return 하는 이름만 view를 찾는 데 사용
	}
	
	// @RequestParam("이름") : parameter로 사용된 변수의 이름과 전달되는 parameter의 이름이 다른 경우 사용
	@GetMapping("/insert")
	public String insert(@RequestParam("ids")ArrayList<String> ids) {
		log.info("insert..." + ids);
		return "insert";
	}
}