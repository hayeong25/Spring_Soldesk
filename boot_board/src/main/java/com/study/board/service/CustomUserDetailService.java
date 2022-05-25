package com.study.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;

import com.study.board.dto.*;
import com.study.board.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("userid 정보 : " + username);
		
		MemberDTO member = mapper.read(username);
		
		log.info("인증 정보 : " + member);
		
		return new CustomUser(member);
	}
}