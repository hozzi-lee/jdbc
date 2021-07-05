package practice;

import java.util.List;
import java.util.Scanner;

public class BookApp {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		// DB를 불러오는 클래스 인스턴스 생성
		BookDao bookDao = new BookDao();

		// 리스트 불러오기
//		showList(bookDao.getBookList());
		
		int bookID;
		String title;
		String pubs;
		String pubDate;
		int authorID;
		
		while (true) {
			System.out.println("1. 책 목록     2. 검색     3. 수정     4.삭제     5. 추가     6. 종료");
			System.out.print("번호> ");
			int num = scan.nextInt();

			switch (num) {
			case 1:
				System.out.println("===== <" + num + "> 책 목록 =====");
				showList(bookDao.getBookList());
				continue;
			case 2:
				System.out.println("===== <" + num + "> 책 검색 =====");
				System.out.print("검색어> ");
				String keyword = scan.next();

				showList(bookDao.BookListSearch(keyword));
				continue;

			case 3:
				System.out.println("===== <" + num + "> 책 정보 수정 =====");
				System.out.println("수정할 책 정보를 입력하세요.");
				System.out.print("책 번호> ");
				bookID = scan.nextInt();

				System.out.print("제목 수정> ");
				title = scan.next();

				System.out.print("출판사 수정> ");
				pubs = scan.next();

				System.out.print("출판일 수정> ");
				pubDate = scan.next();

				System.out.print("작가코드 수정> ");
				authorID = scan.nextInt();

				int udtCount = bookDao.bookUpdate(new BookVo(bookID, title, pubs, pubDate, authorID));
				if (udtCount > 0) {
					System.out.println("[수정되었습니다.]");
				} else {
					System.out.println("[관리자에게 문의하세요(" + udtCount + ")]");
				}
				System.out.println();

				continue;
			case 4:
				System.out.println("===== <" + num + "> 책 삭제 =====");
				System.out.println("삭제할 책 번호를 입력하세요.");
				System.out.print("책 번호> ");
				bookID = scan.nextInt();

				int dltCount = bookDao.bookDelete(new BookVo(bookID));
				if (dltCount > 0) {
					System.out.println("[삭제되었습니다.]");
				} else {
					System.out.println("[관리자에게 문의하세요(" + dltCount + ")]");
				}
				System.out.println();

				continue;
				
			case 5:
				System.out.println("===== <" + num + "> 책 추가 =====");
				System.out.println("추가할 책 정보를 입력하세요.");

				System.out.print("제목> ");
				title = scan.next();

				System.out.print("출판사> ");
				pubs = scan.next();

				System.out.print("출판일> ");
				pubDate = scan.next();

				System.out.print("작가코드> ");
				authorID = scan.nextInt();
				
				int istCount = bookDao.bookInsert(new BookVo(title, pubs, pubDate, authorID));
				if (istCount > 0) {
					System.out.println("[추가되었습니다.]");
				} else {
					System.out.println("[관리자에게 문의하세요(" + istCount + ")]");
				}
				
				continue;
			case 6:
				System.out.println("===== <" + num + "> 종료 =====");
				System.out.println("======= BOOKAPP SHUT DOWN =======");
				System.out.println("======= !!! THANK YOU !!! =======");
				break;
			}
			break;
		}
		scan.close();
	}

	public static void showList(List<BookVo> e) {
		for (BookVo b : e) {
			System.out.println(
					"책 번호: " + b.getBookID() + ",\t"
					+ "제목: " + b.getTitle() + ",\t"
					+ "출판사: " + b.getPubs() + ",\t"
					+ "출판일: " + b.getPubDate() + ",\t"
					+ "작가코드: " + b.getAuthorID());
		}
		
		System.out.println("======================================================");
		System.out.println();
	}

}
