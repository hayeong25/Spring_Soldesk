package com.study.myapp.dao;

import java.sql.*;
public class JdbcUtil {

/* ------------------------------- 자원 close ------------------------------- */
	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
/* ------------------------------- COMMIT ------------------------------- */
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
/* ------------------------------- ROLLBACK ------------------------------- */
	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}