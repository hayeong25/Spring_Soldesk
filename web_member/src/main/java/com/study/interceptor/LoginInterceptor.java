package com.study.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.study.dto.AuthDTO;

public class LoginInterceptor implements HandlerInterceptor {
	
	/*
		────────────────────────── PreHandle ──────────────────────────
		16:53:07.131 [http-nio-9090-exec-8] INFO  c.study.controller.MemberController - [GET] changePwd
		────────────────────────── PostHandle ──────────────────────────
	*/
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		AuthDTO authDto = (AuthDTO)session.getAttribute("login");
		System.out.println("────────────────────────── PreHandle ──────────────────────────");
		
		if(authDto != null) {
			return true;
		}
		
		// 세션이 없다면
		response.sendRedirect("/member/signin");
		return false;
	}
	
	
	
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		
//		System.out.println("────────────────────────── PostHandle ──────────────────────────");
//		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//	}
}
