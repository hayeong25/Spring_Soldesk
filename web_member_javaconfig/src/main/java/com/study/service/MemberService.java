package com.study.service;

import com.study.dto.AuthDTO;
import com.study.dto.ChangeDTO;
import com.study.dto.MemberDTO;

public interface MemberService {
	public boolean register(MemberDTO insertDto);
	public AuthDTO login(String userid, String password);
	public boolean changePassword(ChangeDTO changeDto);
	public boolean leave(String userid);
	public String dupId(String userid);
}
