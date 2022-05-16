package com.study.mapper;

import com.study.dto.*;

public interface UserMapper {
	public SpUser read(String userid);
	public int register(SpUser user);
	public int registerAuth(SpUserAuthority auth);
}