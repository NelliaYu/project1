package com.project.wf.notice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author 왕지민
 * 공지사항글을 삭제하기 위한 클래스
 *
 */
public class FarmManagement_NoticeBoardDelete {
	private ArrayList<FarmManagement_NoticeBoardList> list = new ArrayList<FarmManagement_NoticeBoardList>();
	private static String path = "dat\\11. Noticeboard.dat";
	private Scanner scan = new Scanner(System.in);
	
	/**
	 * 공지사항 글을 자세히보거나 삭제하기 위한 매뉴 메소드
	 * @param login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위한 매개변수
	 */
	public void noticeBoardDeleteMenu(String login) {
		FarmManagement_NoticeBoard board = new FarmManagement_NoticeBoard();
		FarmManagement_NoticeMenu_Admin menuAdmin = new FarmManagement_NoticeMenu_Admin();
		
		
		list = board.boardListup();
		board.boardShowList();
		
		System.out.println("1.자세히 보기");
		System.out.println("2.삭제하기");
		System.out.println("0.뒤로가기");
		System.out.println("=========================");
		System.out.print("번호입력: ");
		
		switch(scan.nextLine()) {
		case "1":
			board.boardChoice(login);
			break;
		case "2":
			noticeBoardDelete();
			noticeBoardWrite();
			break;
		case "0":
			break;
		default:
			System.out.println("[잘못 입력하셨습니다. 다시 입력해주세요.]");
			System.out.println("=========================");
			noticeBoardDeleteMenu(login);
		}
		menuAdmin.noticeMenu_Admin(login);
	}
	
	/**
	 * 공지사항 글을 삭제하기 위한 메소드
	 */
	private  void noticeBoardDelete() {
			
		System.out.print("삭제할 번호: ");
		int seq = Integer.parseInt(scan.nextLine());
		
		for(int i=0;i<list.size();i++) {
			if(Integer.parseInt(list.get(i).getSeq().substring(1,4))==seq) {
				
				list.remove(i);
			}
		}
	}
	
	/**
	 * 공지사항 글이 변동(삭제, 추가)되었을때, 재업로드
	 */
	private void noticeBoardWrite() {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			for (FarmManagement_NoticeBoardList board : list) {
				writer.write(String.format("공지사항 번호: %s\n공지사항 이름: %s\n내용\n%s==========\n",
						board.getSeq(), board.getName(), board.getContent()));
			}
			writer.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
