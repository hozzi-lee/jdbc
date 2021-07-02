package com.javaex.ex04;

import java.util.List;

public class AuthorApp {
	
	// 메인 메서드
	public static void main(String[] args) {

		AuthorDao authorDAO = new AuthorDao();

		// 리스트 출력
		printList(authorDAO.getAuthorList());
		


		
		// 작가등록
		AuthorVo istAuthorVO = new AuthorVo("이호진짱", "이호진짱짱");
		int instCount = authorDAO.authorInsert(istAuthorVO);
		if (instCount > 0) {
			System.out.println("[등록되었습니다.]");
		} else {
			System.out.println("[관리자에게 문의하세요(" + instCount + ")]");
		}
		// 리스트 출력
		printList(authorDAO.getAuthorList());
		
		
		
		// 작가수정
		AuthorVo udtAuthorVO = new AuthorVo(7, "클라이밍", "하고싶다");
		int udtCount = authorDAO.authorUpdate(udtAuthorVO);
		if (udtCount > 0) {
			System.out.println("[수정되었습니다.]");
		} else {
			System.out.println("[관리자에게 문의하세요(" + udtCount + ")]");
		}
		// 리스트 출력
		printList(authorDAO.getAuthorList());

		
		
		// 작가삭제
		AuthorVo dltAuthorVO = new AuthorVo(7);
		int dltCount = authorDAO.authorDelete(dltAuthorVO);
		if (dltCount > 0) {
			System.out.println("[삭제되었습니다.]");
		} else {
			System.out.println("[관리자에게 문의하세요(" + dltCount + ")]");
		}
		// 리스트 출력
		printList(authorDAO.getAuthorList());
		
		/*
		// 작가 1명의 정보 출력
		??? = authorDAO.getAuthorOne(3);
		 */
	}
	
	// 리스트 출력 메서드
	public static void printList(List<AuthorVo> e) {
		for (AuthorVo author : e) {
			System.out.println(
					"작가코드번호: " + author.getAuthorID() + 
					",\t작가이름: " + author.getAuthorName() + 
					",\t작가정보: " + author.getAuthorDesc()
					);
		}
		System.out.println();
		System.out.println("====== NEXT ======");
		System.out.println();
		
		/*
		for (int i = 0; i < authorList.size(); i++) {
			AuthorVo authorVo = authorList.get(i);
			
			int authorID = authorVo.getAuthorID();
			String authorName = authorVo.getAuthorName();
			String authorDesc = authorVo.getAuthorDesc();
			
			System.out.println(
					"작가코드번호: " + authorID + 
					", 작가이름: " + authorName + 
					", 작가정보: " + authorDesc
					);
		}
		 */
	}

}
