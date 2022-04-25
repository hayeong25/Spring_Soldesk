package com.study.myapp.dao;

import static com.study.myapp.dao.JdbcUtil.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import org.springframework.stereotype.Repository;
import com.study.myapp.dto.BoardDTO;

@Repository // 객체 생성 (객체 저장소)
public class BoardDAO {
	private Connection con;
	
	public int insert(BoardDTO insertDTO) {
		PreparedStatement pstmt = null;
		
		String SQL = "INSERT INTO spring_board(bno, title, content, writer) VALUES(seq_board.nextval, ?, ?, ?)";
		
		int result = 0;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, insertDTO.getTitle());
			pstmt.setString(2, insertDTO.getContent());
			pstmt.setString(3, insertDTO.getWriter());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		
		return result;
	}
	
	public List<BoardDTO> select() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "SELECT * FROM spring_board";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getDate("regdate"));
				dto.setUpdatedate(rs.getDate("updatedate"));
				
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
	
	public int update(int bno, String title, String content, Date updatedate) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String SQL = "UPDATE spring_board SET title = ?, content = ?, updatedate = SYSDATE WHERE bno = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, bno);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		
		return result;
	}
	
	public int delete(int bno) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String SQL = "DELETE FROM spring_board WHERE bno = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, bno);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		
		return result;
	}
	
	public BoardDTO selectOne(int bno) {
		BoardDTO dto = new BoardDTO();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "SELECT * FROM spring_board WHERE bno = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getDate("regdate"));
				dto.setUpdatedate(rs.getDate("updatedate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		
		return dto;
	}
}