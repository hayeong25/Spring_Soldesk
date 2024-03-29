package com.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;

import com.study.dto.CustomUser;
import com.study.dto.SpUser;
import com.study.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("userid 정보 : " + username);
		
		SpUser spUser = mapper.read(username);
		
		log.info("인증 정보 : " + spUser);
		
		return new CustomUser(spUser);
	}

}