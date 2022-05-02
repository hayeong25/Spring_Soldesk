package com.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.dto.AuthDTO;
import com.study.dto.ChangeDTO;
import com.study.dto.MemberDTO;
import com.study.mapper.MemberMapper;

@Service("service")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public boolean register(MemberDTO insertDto) {
		return mapper.register(insertDto) > 0 ? true : false;
	}
	
	@Override
	public AuthDTO login(String userid, String password) {
		return mapper.login(userid, password);
	}
	
	@Override
	public boolean changePassword(ChangeDTO changeDto) {
		return mapper.changePassword(changeDto) > 0 ? true : false;
	}
	
	@Override
	public boolean leave(String userid) {
		return mapper.leave(userid) > 0 ? true : false;
	}
	
	@Override
	public String dupId(String userid) {
		return mapper.dupId(userid);
	}
	
}
