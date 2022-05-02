package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.study.dto.NumDTO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/calc/*")
public class AddController {
	
	@GetMapping("/add")
	public void addGet() {
		log.info("add GET ....");
	}
	
	/*
		Mapping 에서 Parameter 값을 가져오는 방법
        ① 변수명 사용
        ② DTO 객체 사용
        ③ HttpServletRequest 객체 사용 ( request.getParameter ) : int 자동 형변환 X
	*/
	
	// @RequestParam("name") : Parameter로 사용된 변수의 이름과 전달되는 Parameter의 이름이 다른 경우 사용
//	@PostMapping("/add")
//	public void addPost(@RequestParam(value="num1", required=false, defaultValue="0")int op1, @RequestParam(value="num2", required=false, defaultValue="0")int op2) {
//		log.info("add POST ....");
//		log.info("num1 + num2 = " + (op1 + op2));
//	}
	
	// @ModelAttribute("name") : 바인딩 객체의 name 변경 & Model 객체에 값을 담는 건과 같은 기능
	@PostMapping("/add")
	public void addPost(@ModelAttribute("dto")NumDTO dto, Model model) {
		log.info("add POST ....");
		int result = (dto.getNum1() + dto.getNum2());
		log.info("num1 + num2 = " + result);
		
		// result 값을 add.jsp에서 보여주기 : Model 객체(request.setAttribute()와 같은 개념)
		model.addAttribute("result", result);
	}
}