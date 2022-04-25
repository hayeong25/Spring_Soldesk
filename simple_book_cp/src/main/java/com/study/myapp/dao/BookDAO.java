package com.study.myapp.dao;

import static com.study.myapp.dao.JdbcUtil.*;
import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.myapp.dto.BookDTO;

@Repository // 객체를 한 번만 생성해서 필요한 곳에 넘겨줌
public class BookDAO {
	
	@Autowired
	private DataSource ds;
	// private Connection con;
		
	public List<BookDTO>  select() {
		List<BookDTO> list = new ArrayList<BookDTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		String SQL = "SELECT * FROM booktbl";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setCode(rs.getInt("code"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setPrice(rs.getInt("price"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		
		return list;
	}
	
	public int insert(BookDTO insertDTO) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		Connection con = null;
		
		String SQL = "INSERT INTO booktbl VALUES(?, ?, ?, ?)";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, insertDTO.getCode());
			pstmt.setString(2, insertDTO.getTitle());
			pstmt.setString(3, insertDTO.getWriter());
			pstmt.setInt(4, insertDTO.getPrice());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		
		return result;
	}
		
}