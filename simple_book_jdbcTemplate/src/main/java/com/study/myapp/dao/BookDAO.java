package com.study.myapp.dao;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.study.myapp.dto.BookDTO;

@Repository // 객체 생성
public class BookDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<BookDTO>  select() {
		String SQL = "SELECT * FROM booktbl";
		
		return jdbcTemplate.query(SQL, new BookRowMapper());
	}
	
	public int insert(BookDTO insertDTO) {
		String SQL = "INSERT INTO booktbl VALUES(?, ?, ?, ?)";
		
		int result = jdbcTemplate.update(SQL, insertDTO.getCode(), insertDTO.getTitle(), insertDTO.getWriter(), insertDTO.getPrice());
		
		return result;
	}

	public int update(int code, int price) {
		String SQL = "UPDATE booktbl SET price = ? WHERE code = ?";
		
		int result = jdbcTemplate.update(SQL, price, code);
		
		return result;
	}
	
	public int delete(int code) {
		String SQL = "DELETE FROM booktbl WHERE code = ?";
		
		int result = jdbcTemplate.update(SQL, code);
		
		return result;
	}
	
}