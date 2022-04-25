package com.study.mapper;

import org.apache.ibatis.annotations.Param;

import com.study.dto.PersonDTO;

public interface PersonMapper {
	// @Insert("SQL문") => DAO 역할
	// #{id}, #{name} => ?로 변경
//	@Insert("insert into person(id, name) values(#{id, #{name})")
//	public int insertPerson(@Param("id") String id, @Param("name") String name);
	// 매개변수가 2개 이상인 경우 @Param으로 어떤 변수값을 어디에 넣어줘야 하는지 지정해야 함. 안 그러면 BindingException 발생
	
	// 진짜 실행은 PersonMapper.xml에서
	public int insert(@Param("id") String id, @Param("name") String name);
	public int update(@Param("id") String id, @Param("name") String name);
	public int delete(String id);
	public PersonDTO select(String id);
}