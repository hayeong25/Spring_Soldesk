package com.study.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class CustomLoginSuccessHander implements AuthenticationSuccessHandler {

	// 로그인 성공 후 기본으로 동작하는 핸들러 대신 개발자가 원하는 곳으로 이동
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// 부여된 권한 확인하기
		List<String> roleNames = new ArrayList<String>();
		authentication.getAuthorities().forEach(auth -> roleNames.add(auth.getAuthority()));
		
		// 권한이 ROLE_ADMIN인 경우, admin-page로 이동
		if(roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("/admin-page");
			return;
		}
		
		// 권한이 ROLE_USER인 경우, user-page로 이동
		if(roleNames.contains("ROLE_USER")) {
			response.sendRedirect("/user-page");
			return;
		}
		
		// 권한이 없는 경우
		response.sendRedirect("/");
	}

}
