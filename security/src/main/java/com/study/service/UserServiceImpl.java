package com.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.dto.*;
import com.study.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder encoder; // Spring에서 제공하는 암호화 클래스 (단방향)

	@Transactional
	@Override
	public boolean register(SpUser user) {
		// 사용자가 입력한 비밀번호를 암호화
		user.setPassword(encoder.encode(user.getPassword()));
		
		// 회원가입
		boolean result = mapper.register(user) == 1;
		
		// 권한 부여
		SpUserAuthority auth = new SpUserAuthority(user.getUserid(), "ROLE_USER");
		mapper.registerAuth(auth);
		
		auth = new SpUserAuthority(user.getUserid(), "ROLE_ADMIN");
		mapper.registerAuth(auth);
		
		return result;
	}
}