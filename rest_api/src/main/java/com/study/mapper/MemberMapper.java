package com.study.mapper;

import org.apache.ibatis.annotations.Param;

import com.study.dto.AuthDTO;
import com.study.dto.ChangeDTO;
import com.study.dto.MemberDTO;

public interface MemberMapper {
	public int register(MemberDTO register);
	public AuthDTO login(@Param("userid") String userid, @Param("password") String password);
	public int changePassword(ChangeDTO changeDto);
	public int leave(String userid);
	public String dupId(String userid);
}
