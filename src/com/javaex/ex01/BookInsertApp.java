package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookInsertApp {
	public static void main(String[] args) {

//		java.sql.Date date = new java.sql.Date(1993/10/07);

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
//				ResultSet rs = null; // SELECT 문 불러올때 사용

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "webdb", "webdb");
			// 3. SQL문 준비 / 바인딩 / 실행
			pstmt = conn.prepareStatement(" "
					+ " INSERT INTO books "
					+ " VALUES ( sqc_book_id.NEXTVAL, ?, ?, ?, ? ) ");
			pstmt.setString(1, "이호진 자서전");
			pstmt.setString(2, "호북");
			pstmt.setString(3, "1993/10/07");
			pstmt.setInt(4, 3);

			int count = pstmt.executeUpdate();

			// 4. 결과처리
			System.out.println(count + "건이 저장되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				/*
				 * if (rs != null) { rs.close(); }
				 */
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}

}
