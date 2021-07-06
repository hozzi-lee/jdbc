package practice.oracleminipro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//사진파일을 그대로 작성해서 오류 확인하기

public class PhoneDao {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";
	
	private void getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR - 드라이버 로딩 실패: " + e);
		} catch (SQLException e) {
			System.out.println("ERROR: " + e);
		}
	}
	
	private void getClose() {
		try {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			System.out.println("ERROR: " + e);
		}
	}
	
	public int phoneInsert(PhoneVo p) {
		int count = -1;
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" INSERT INTO "
					+ " 		person "
					+ " VALUES "
					+ " 	( seq_person_id.NEXTVAL, ?, ?, ? ) "
					);
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getHp());
			pstmt.setString(3, p.getCompany());
			
			count = pstmt.executeUpdate();
			
//			System.out.println("[" + p.getName() + "] 등록 되었습니다."); 삭제
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getClose();
		
		return count;
	}
	
	public List<PhoneVo> phoneList() {
		List<PhoneVo> phoneList = new ArrayList<PhoneVo>();
		
		getConnection();

		try {
			pstmt = conn.prepareStatement(
					" SELECT "
					+ " 	person_id 번호, " // 사진에 콤마 작성 안함
					+ " 	name 이름, " // 사진에 콤마 작성 안함
					+ " 	hp 휴대전화, " // 사진에 콤마 작성 안함
					+ " 	company 회사전화 "
					+ " FROM "
					+ " 	person "
					);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				phoneList.add(new PhoneVo(rs.getInt("번호"), rs.getString("이름"), rs.getString("휴대전화"), rs.getString("회사전화")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getClose();
		
		return phoneList;
	}
	
	public List<PhoneVo> phoneList(String k) { // 제네릭 사용해 보겠습니다.
		List<PhoneVo> phoneList = new ArrayList<PhoneVo>();
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" SELECT "
					+ " 	person_id I, " // 사진에 콤마 작성 안함, 별명 대문자로 변경
					+ " 	name N, " // 사진에 콤마 작성 안함, 별명 대문자로 변경
					+ " 	hp H, " // 사진에 콤마 작성 안함, 별명 대문자로 변경
					+ " 	company C " // 별명 대문자로 변경
					+ " FROM "
					+ " 	person "
					+ " WHERE "
					+ " 	name LIKE '%" + k + "%' "
					+ " 	OR hp LIKE '%" + k + "%' "
					+ " 	OR company LIKE '%" + k + "%' "
					);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				phoneList.add(new PhoneVo(rs.getInt("I"), rs.getString("N"), rs.getString("H"), rs.getString("C")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getClose();
		
		return phoneList;
	}
	
	public int phoneUpdate(PhoneVo p) {
		int count = -1;
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" UPDATE "
					+ " 	person "
					+ " SET "
					+ " 	name = ?, " // 사진에 콤마 작성 안함
					+ " 	hp = ?, " // 사진에 콤마 작성 안함
					+ " 	company = ? "
					+ " WHERE "
					+ " 	person_id = ? "
					);
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getHp());
			pstmt.setString(3, p.getCompany());
			pstmt.setInt(4, p.getPersonID());
			
			count = pstmt.executeUpdate();
			
//			System.out.println("[" + p.getPersonID() + "]번 수정 되었습니다."); 삭제
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getClose();
		
		return count;
	}
	
	public int phoneDelete(PhoneVo p) {
		int count = -1;
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement(
					" DELETE FROM "
					+ " 		person "
					+ " WHERE "
					+ " 	person_id = ? "
					);
			pstmt.setInt(1, p.getPersonID());
			
			count = pstmt.executeUpdate();
			
//			System.out.println("[" + p.getPersonID() + "]번 삭제 되었습니다."); 삭제
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getClose();
		
		return count;
	}

}
