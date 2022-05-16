package com.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.dto.*;
import com.study.mapper.MemberMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder encoder; // Spring에서 제공하는 암호화 클래스 (단방향)

	@Transactional
	@Override
	public boolean register(MemberDTO member) {
		// 사용자가 입력한 비밀번호를 암호화
//		member.setUserpw(encoder.encode(member.getUserpw()));
		
		// 회원가입
//		boolean result = mapper.register(member) == 1;
		
		// 권한 부여
//		AuthDTO auth = new AuthDTO(member.getUserid(), "ROLE_USER");
//		mapper.registerAuth(auth);
		
		return false;
		
	}
}