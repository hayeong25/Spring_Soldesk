package com.study.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.study.dto.BookDTO;

public interface BookMapper {
	public List<BookDTO> getList();
	public int insert(BookDTO insertDTO);
	public int update(@Param("code")int code, @Param("price")int price);
	public int delete(int code);
	public List<BookDTO> search(@Param("criteria")String criteria, @Param("keyword")String keyword);
}