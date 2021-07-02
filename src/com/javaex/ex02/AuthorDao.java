package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	// field

	// constructors

	// method getter/setter

	// method
	// 작가 리스트 메서드
	public List<AuthorVo> getAuthorList() {
		// DB값을 가져와서 ArrayList로 전달
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
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
					" SELECT "
					+ "		author_id, "
					+ "		author_name, "
					+ "		author_desc "
					+ " FROM "
					+ "		authors "
					+ " ORDER BY "
					+ " 	author_id ASC ");

			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
				int authorID = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");

				AuthorVo authorVO = new AuthorVo(authorID, authorName, authorDesc);
				
				authorList.add(authorVO);

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
		
		return authorList;
	}

	// 작가 1명 불러오기 메서드
	public void getAuthorOne() {

	}

	// 작가 등록 메서드
	public int authorInsert(String authorName, String authorDesc) {
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int count = -1;
		
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "webdb", "webdb");
		    // 3. SQL문 준비 / 바인딩 / 실행
			pstmt = conn.prepareStatement(
					" INSERT INTO authors "
					+ " VALUES ( sqc_author_id.NEXTVAL, ?, ? ) "
					);
			
			pstmt.setString(1, authorName);
			pstmt.setString(2, authorDesc);
			
			count = pstmt.executeUpdate();
		    
		    // 4.결과처리
			System.out.println(count + "건 등록");

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
		
		return count; // 성공 개수 리턴
	}

	// 작가 수정 메서드
	public int authorUpdate(int authorID, String authorName, String authorDesc) {
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int count = -1;
		
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "webdb", "webdb");
		    // 3. SQL문 준비 / 바인딩 / 실행
		    pstmt = conn.prepareStatement(
		    		" UPDATE "
		    		+ "		authors "
		    		+ " SET "
		    		+ " 	author_name = ?, "
		    		+ " 	author_desc = ? "
		    		+ " WHERE "
		    		+ " 	author_id = ? "
		    		);
		    
		    pstmt.setString(1, authorName);
		    pstmt.setString(2, authorDesc);
		    pstmt.setInt(3, authorID);
		    
		    count = pstmt.executeUpdate();
		    // 4.결과처리
		    System.out.println(count + "건 수정");

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

		
		return count;

	}
	
	// 작가 삭제 메서드
	public int authorDelete(int authorID) {
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int count = -1;
		
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "webdb", "webdb");
		    // 3. SQL문 준비 / 바인딩 / 실행
			pstmt = conn.prepareStatement(
					" DELETE FROM authors "
					+ " WHERE author_id = ? "
					);
		    pstmt.setInt(1, authorID);
		    
		    count = pstmt.executeUpdate();
		    // 4.결과처리
		    System.out.println(count + "건 삭제");

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
		
		return count;


	}

}
