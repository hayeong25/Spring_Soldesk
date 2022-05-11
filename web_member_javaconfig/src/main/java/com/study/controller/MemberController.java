package com.study.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.dto.AuthDTO;
import com.study.dto.ChangeDTO;
import com.study.dto.MemberDTO;
import com.study.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/member/*")
@Slf4j
@Controller
public class MemberController {
	
	@Autowired
	private MemberService service; 
	
	// step 1 페이지 보여주는 Controller ( 약관동의 )
	@GetMapping("/step1")
	public void step1() {
		log.info("[GET] step1");
	}
	
//	@PostMapping("/step1")
//	public String step1(boolean agree) {
//		log.info("[POST] step1");
//		try {
//			if(agree){
//				return "redirect:/member/step2";
//			}
//		} catch (Exception e) {
//			return "redirect:/";
//		}
//		return "redirect:/";
//	}
	
	// step 2 페이지 보여주는 Controller ( 회원가입 )
	@PostMapping("/step2")
	public String step2(boolean agree, RedirectAttributes rttr) {
		log.info("[POST] step1");
		if(!agree) {
			rttr.addFlashAttribute("check", "false");
			return "redirect:/member/step1";
		}
		return "/member/step2";
	}
	
	// step2 요청 처리하는 컨트롤러 작성
	// 회원가입 서비스 호출
	// 회원가입 성공시 signin 보여주기 ( redirect ) :: 실패 ) 회원가입 페이지
	@PostMapping("/regist")
	public String step2(MemberDTO register) {
		log.info("[POST] step2");
		try {
			if(service.register(register)) {
				return "redirect:/member/signin";
			}
		} catch (Exception e) {
			return "/member/step2";
		}
		return "/member/step2";
	}
	
	@GetMapping("/signin")
	public void signin() {
		log.info("[GET] sign in");
	}
	
	@PostMapping("/signin")
	public String login(String userid, String password, HttpSession session) {
		log.info("[POST] sign in");
		AuthDTO loginDto = service.login(userid, password);
		if(loginDto != null) {
			session.setAttribute("login", loginDto);
			return "index";
		}
		return "redirect:/member/signin";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.info("[GET] logout");
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/changePwd")
	public void changePwd() {
		log.info("[GET] changePwd");
	}
	
	@PostMapping("/changePwd")
	public String changePwd(ChangeDTO changeDTO, HttpSession session, RedirectAttributes rttr) {
		log.info("[POST] changePwd");
		
		AuthDTO getId = (AuthDTO)session.getAttribute("login");
		changeDTO.setUserid(getId.getUserid());
		if(service.login(changeDTO.getUserid(), changeDTO.getPassword()) != null) {
			try {
				if(service.changePassword(changeDTO)) {
					session.invalidate();
					return "redirect:/member/signin";
				}
			} catch (Exception e) {
				rttr.addFlashAttribute("error", "변경에 오류가 발생했습니다.");
				return "redirect:/member/changePwd";
			}
		}else {
			rttr.addFlashAttribute("error", "현재 비밀번호를 확인해주세요");
			return "redirect:/member/changePwd";
		}
		return "redirect:/member/changePwd";
	}
	
	@GetMapping("/leave")
	public void leave() {
		log.info("[GET] leave");
	}

	@PostMapping("/leave")
	public String leave(String userid, @RequestParam("current_password") String password, RedirectAttributes rttr, HttpSession session) {
		log.info("[POST] leave");
		if(service.login(userid, password) != null) {
			try {
				if(service.leave(userid)) {
					session.invalidate();
					return "index";
				}
			} catch (Exception e) {
				rttr.addFlashAttribute("error", "탈퇴 과정에서 문제가 발생했습니다.");
				return "redirect:/member/leave";
			}
		}else {
			rttr.addFlashAttribute("error", "비밀번호가 일치하지 않습니다.");
			return "redirect:/member/leave";
		}
		return "redirect:/member/leave";
	}
	
	/*
		@Controller : Controller 종료 시점에 view 결정 
				void + /[path]/~	: /WEB-INF/views/[path]/~.jsp
				String + "~"		: /WEB-INF/views/~.jsp
		@ResponseBody : return 값이 JSP 가 아님을 명시
	*/
	
	// ajax CheckID
	@ResponseBody // JSP 로 페이지를 이동시키지 않고, 값을 출력 ( Ajax )
	@PostMapping("/checkId")
	public String checkId(String userid) {
		log.info("[POST] checkId");
		
		// checkUserId 값이 있다면 중복, null 이라면 사용 가능
		if(service.dupId(userid) != null) {
			return "false";
		}
		return "true";
	}
	
	
	/*
		Interceptor 
			> 특정한 URI 호출을 가로채는 역할
			> 다수의 컨트롤러에 대해 동일한 기능 제공

		HandlerInterceptor
			> 컨트롤러에서는 파라미터와 결과 데이터를 만들어 냄
			> 인터셉터는 웹과 관련된 처리를 도와주는 역할
			
			
			DispatcherServlet		HandlerInterceptor		Controller(HandlerAdapter		View
			
				│	>	PreHandle()		>	│
				│	<	return()		<	│
				│	preHandle(return : true)
				│
				│	>			@RequestMapping Method			>	│
				│													│
				│
				│
				│
				│
				│
				│
					
	*/
}
