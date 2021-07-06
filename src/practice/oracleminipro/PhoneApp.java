package practice.oracleminipro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

//사진파일을 그대로 작성해서 오류 확인하기

public class PhoneApp {

	public static void main(String[] args) throws IOException { // main 메서드 추가..........
		BufferedReader bfR = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bfW = new BufferedWriter(new OutputStreamWriter(System.out));
		
		PhoneDao phoneDao = new PhoneDao();
		
		int personID;
		String name, hp, company;
		int count; // 추가
		
		bfW.write("***************************************\n"
				+ "*           전화번호 관리 프로그램           *\n"
				+ "***************************************");
		bfW.newLine(); // 추가
		bfW.flush();
		
		while (true) {
			bfW.write("1.리스트  2.등록  3.수정  4.삭제  5.검색  6.종료\n"
					+ "--------------------------------------\n"
					+ ">메뉴번호: ");
			bfW.flush();
			int num = Integer.parseInt(bfR.readLine());
			
			switch (num) {
			
			case 1:
				bfW.write("<1.리스트>");
				bfW.newLine(); // 추가
				bfW.flush(); // 추가
				showList(phoneDao.phoneList());
//				bfW.newLine(); 삭제
				
				continue;
				
			case 2:
				bfW.write("<2.등록>");
				bfW.newLine(); // 추가
				bfW.write("이름 > ");
				bfW.flush();
				name = bfR.readLine();
				
				bfW.write("휴대전화 > ");
				bfW.flush();
				hp = bfR.readLine();
				
				bfW.write("회사번호 > ");
				bfW.flush();
				company = bfR.readLine();
				
				// count, if-else 추가
				count = phoneDao.phoneInsert(new PhoneVo(name, hp, company));
				if (count > 0) {
					bfW.write("[" + name + "] 등록 되었습니다.");
				} else {
					bfW.write("ERROR: " + count + " [관리자에게 문의하세요.]");
				}
				bfW.newLine();
				bfW.newLine(); // 추가
				
				continue;
				
			case 3:
				bfW.write("<3.수정>");
				bfW.newLine(); // 추가
				bfW.write("번호 > ");
				bfW.flush();
				personID = Integer.parseInt(bfR.readLine());
				
				bfW.write("이름 > ");
				bfW.flush();
				name = bfR.readLine();
				
				bfW.write("휴대전화 > ");
				bfW.flush();
				hp = bfR.readLine();
				
				bfW.write("회사번호 > ");
				bfW.flush();
				company = bfR.readLine();
				
				// count, if-else 추가
				count = phoneDao.phoneUpdate(new PhoneVo(personID, name, hp, company));
				if (count > 0) {
					bfW.write("[" + personID + "]번 수정 되었습니다.");
				} else {
					bfW.write("ERROR: " + count + " [관리자에게 문의하세요.]");
				}
				bfW.newLine();
				bfW.newLine(); // 추가
				
				continue;
				
			case 4:
				bfW.write("<4.삭제>");
				bfW.newLine(); // 추가
				bfW.write("번호 > ");
				bfW.flush();
				personID = Integer.parseInt(bfR.readLine());
				
				// count, if-else 추가
				count = phoneDao.phoneDelete(new PhoneVo(personID));
				if (count > 0) {
					bfW.write("[" + personID + "]번 삭제 되었습니다.");
				} else {
					bfW.write("ERROR: " + count + " [관리자에게 문의하세요.]");
				}
				bfW.newLine();
				bfW.newLine(); // 추가
				
				continue;
				
			case 5:
				bfW.write("<5.검색>");
				bfW.newLine(); // 추가
				bfW.write("검색어 > ");
				bfW.flush();
				String keyword = bfR.readLine();
				
				showList(phoneDao.phoneList(keyword));
				
				continue;
				
			case 6:
				bfW.newLine();
				bfW.write("***************************************\n"
						+ "*               감사합니다               *\n"
						+ "***************************************");
				bfW.flush(); // 추가
				
				break;
				
			default:
				bfW.write("[다시 입력해주세요.]");
				
				continue;
			
			}
			
			break;

		}

	}
	
	public static void showList(List<PhoneVo> e) {
		for (PhoneVo p : e) {
			System.out.println(
					"번호: " + p.getPersonID() + "       "
					+ "이름: " + p.getName() + "       "
					+ "휴대전화: " + p.getHp() + "       "
					+ "회사번호: " + p.getCompany()
					);
		}
		System.out.println();
	}

}
