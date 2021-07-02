package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelectApp {
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

			pstmt = conn.prepareStatement(" "
					+ " SELECT "
					+ "		book_id, "
					+ "		title, "
					+ "		pubs, "
					+ "		pub_date, "
					+ "		author_id "
					+ " FROM "
					+ "		books ");

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int bookID = rs.getInt("book_id");
				String bookTitle = rs.getString("title");
				String bookPubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorID = rs.getInt("author_id");

				System.out.println(bookID + ", " + bookTitle + ", " + bookPubs + ", " + pubDate + ", " + authorID);

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
