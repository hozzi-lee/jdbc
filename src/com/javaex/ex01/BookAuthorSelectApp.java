package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookAuthorSelectApp {
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

			pstmt = conn.prepareStatement(
					" SELECT bok.book_id 책코드번호, bok.title 책제목, bok.pubs 출판사, bok.pub_date 출판일, bok.author_id 책의작가코드, atho.author_id 작가코드번호, atho.author_name 작가이름, atho.author_desc 작가정보 FROM books bok FULL JOIN authors atho ON bok.author_id = atho.author_id ");

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int bookID = rs.getInt("책코드번호");
				String bookTitle = rs.getString("책제목");
				String bookPubs = rs.getString("출판사");
				String pubDate = rs.getString("출판일");
				int bokAuthorID = rs.getInt("책의작가코드");
				int athoAuthorID = rs.getInt("작가코드번호");
				String authorName = rs.getString("작가이름");
				String authorDesc = rs.getString("작가정보");

				System.out.println(bookID + ", " + bookTitle + ", " + bookPubs + ", " + pubDate + ", " + bokAuthorID
						+ ", " + athoAuthorID + ", " + authorName + ", " + authorDesc);

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
