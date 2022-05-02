package com.study.controller;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.dto.RegisterDTO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "home"; // Controller의 return 타입이 String인 경우, return 값으로만 JSP 페이지를 찾음 => WEB-INF/views/home.jsp
	}
	
	/*
		sendRedirect 방식으로 움직일 때 값을 전송하는 방법
		1) rttr.addAttribute("age", 10) : 주소 줄에 age 라는 이름으로 10 보내는 방식 ex) http://~?age=10
			=> path += "?page=" + page + "&amount=" + amount 같이 안 해도 됨
		2) rttr.addFlashAttribute("num", "15") : session 객체에 담는 방식 (일회성)
	*/
	
	@GetMapping("/doB")
	public String doB(RedirectAttributes rttr) {
		rttr.addAttribute("age", 10);
		rttr.addFlashAttribute("num", "15");
		return "redirect:/";
	}
	
	@GetMapping("/doC")
	public ModelAndView doC() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		mav.addObject("num", 35);
		return mav;
	}
	
	@GetMapping("/doD")
	public RegisterDTO regist() {
		return new RegisterDTO();
	}
}