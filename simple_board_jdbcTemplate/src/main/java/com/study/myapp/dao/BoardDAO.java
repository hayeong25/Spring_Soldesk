package com.study.myapp.dao;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.study.myapp.dto.BoardDTO;

@Repository // 객체 생성 (객체 저장소)
public class BoardDAO {
	
	@Autowired
	// private DataSource ds;
	// private Connection con;
	private JdbcTemplate jdbcTemplate;
	
	public int insert(BoardDTO insertDTO) {
		String SQL = "INSERT INTO spring_board(bno, title, content, writer) VALUES(seq_board.nextval, ?, ?, ?)";
		
		int result = jdbcTemplate.update(SQL, insertDTO.getTitle(), insertDTO.getContent(), insertDTO.getWriter());
		
		return result;
	}
	
	public List<BoardDTO> select() {
		String SQL = "SELECT * FROM spring_board";
		
		return jdbcTemplate.query(SQL, new BoardRowMapper());
	}
	
	public int update(BoardDTO updateDTO) {
		String SQL = "UPDATE spring_board SET title = ?, content = ?, updatedate = SYSDATE WHERE bno = ?";
		
		int result = jdbcTemplate.update(SQL, updateDTO.getTitle(), updateDTO.getContent(), updateDTO.getBno());
		
		return result;
	}
	
	public int delete(int bno) {
		String SQL = "DELETE FROM spring_board WHERE bno = ?";
		
		return jdbcTemplate.update(SQL, bno);
	}
	
	public BoardDTO selectOne(int bno) {
		String SQL = "SELECT * FROM spring_board WHERE bno = ?";
		
		return jdbcTemplate.queryForObject(SQL, new BoardRowMapper(), bno);
	}
}