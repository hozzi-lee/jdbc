package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// Connection
	private void getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error: Failed to load Driver " + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
	}
	
	// close
	private void close() {
		try {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
	}
	
	// SELECT
	public List<BookVo> getBookList() {
		List<BookVo> bookList = new ArrayList<BookVo>();
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" SELECT "
					+ " 	book_id 책번호, "
					+ " 	title 책제목, "
					+ " 	pubs 출판사, "
					+ " 	pub_date 출판일, "
					+ " 	author_id 작가코드 "
					+ " FROM "
					+ "		books "
					);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bookList.add(new BookVo(rs.getInt("책번호"), rs.getString("책제목"), rs.getString("출판사"), rs.getString("출판일"), rs.getInt("작가코드")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return bookList;
	}
	
	// SELECT Search
	public List<BookVo> BookListSearch(String keyword) {
		List<BookVo> bookList = new ArrayList<BookVo>();
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" SELECT "
							+ " 	book_id 책번호, "
							+ " 	title 책제목, "
							+ " 	pubs 출판사, "
							+ " 	pub_date 출판일, "
							+ " 	author_id 작가코드 "
							+ " FROM "
							+ "		books "
							+ " WHERE "
							+ " 	title LIKE '%" + keyword + "%' "
							+ " 	OR pubs LIKE '%" + keyword + "%' "
					);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bookList.add(new BookVo(rs.getInt("책번호"), rs.getString("책제목"), rs.getString("출판사"), rs.getString("출판일"), rs.getInt("작가코드")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		close();
		
		
		return bookList;
	}
	
	
	// UPDATE
	public int bookUpdate(BookVo b) {
		int count = -1;
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" UPDATE "
					+ " 	books "
					+ " SET "
					+ " 	title = ?, "
					+ " 	pubs = ?, "
					+ " 	pub_date = ?, "
					+ " 	author_id = ? "
					+ " WHERE "
					+ " 	book_id = ? "
					);
			
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getPubs());
			pstmt.setString(3, b.getPubDate());
			pstmt.setInt(4, b.getAuthorID());
			pstmt.setInt(5, b.getBookID());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건 수정");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return count;
	}
	
	// DELETE
	public int bookDelete(BookVo b) {
		
		int count = -1;
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" DELETE FROM "
					+ " 		books "
					+ " WHERE "
					+ " 	book_id = ? "
					);
			
			pstmt.setInt(1, b.getBookID());
			count = pstmt.executeUpdate();
			System.out.println(count + "건 삭제");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return count;
	}
	
}
