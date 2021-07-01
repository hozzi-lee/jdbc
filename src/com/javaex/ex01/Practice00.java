package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Practice00 {
	public static void main(String[] args) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "webdb", "webdb");
			// 3. SQL문 준비 / 바인딩 / 실행
//			String query = "";
//			query += " SELECT book_id 책코드번호, ";
//			query += "        title 책제목, ";
//			query += "        pubs 출판사 ";
//			query += " FROM books ";

			pstmt = conn.prepareStatement(" SELECT book_id 책코드번호, title 책제목, pubs 출판사 FROM books");
//			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
//				String bookID = rs.getString("책코드번호");
//				String bookTitle = rs.getString("책제목");
//				String bookPubs = rs.getString("출판사");
				System.out.println(rs.getString("책코드번호") + ", " + rs.getString("책제목") + ", " + rs.getString("출판사"));
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
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
