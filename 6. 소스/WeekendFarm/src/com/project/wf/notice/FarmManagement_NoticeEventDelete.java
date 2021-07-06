package com.project.wf.notice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * 
 * @author 왕지민
 * 프로모션글을 삭제하기 위한 클래스
 *
 */
public class FarmManagement_NoticeEventDelete {
	private ArrayList<FarmManagement_NoticeEventList> list = new ArrayList<FarmManagement_NoticeEventList>();
	private static String path = "dat\\10. Promotion.dat";
	private Scanner scan = new Scanner(System.in);
	
	/**
	 * 프로모션 글을 자세히보거나 삭제하기 위한 매뉴 메소드
	 * @param login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위한 매개변수
	 */
	public void noticeEventDeleteMenu(String login) {
		FarmManagement_NoticeEvent event = new FarmManagement_NoticeEvent();
		FarmManagement_NoticeMenu_Admin menuAdmin = new FarmManagement_NoticeMenu_Admin();
		
		list = event.eventListup();
		event.eventShowList();
		
		System.out.println("1.자세히 보기");
		System.out.println("2.삭제하기");
		System.out.println("0.뒤로가기");
		System.out.println("=========================");
		System.out.print("번호입력: ");
		
		switch(scan.nextLine()) {
		case "1":
			event.eventChoice(login);
			break;
		case "2":
			noticeEventDelete();
			eventWrite();
			break;
		case "0":
			menuAdmin.noticeMenu_Admin(login);
			break;
		default:
			System.out.println("[잘못 입력하셨습니다. 다시 입력해주세요.]");
			System.out.println("=========================");
			noticeEventDeleteMenu(login);
		}
		menuAdmin.noticeMenu_Admin(login);
	}
	
	/**
	 * 프로모션 글을 삭제하기 위한 메소드
	 */
	private  void noticeEventDelete() {
			
		System.out.print("삭제할 번호");
		int seq = Integer.parseInt(scan.nextLine());
		
		for(int i=0;i<list.size();i++) {
			if(Integer.parseInt(list.get(i).getSeq().substring(1,4))==seq) {
				
				list.remove(i);
			}
		}
	}
	
	/**
	 * 프로모션 글이 변동(삭제, 추가)되었을때, 재업로드
	 */
	private void eventWrite() {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			for (FarmManagement_NoticeEventList event : list) {
				writer.write(String.format("프로모션 번호: %s\n프로모션 이름: %s\n프로모션 종류: %s\n내용\n%s==========\n",
						event.getSeq(), event.getName(), event.getType(), event.getContent()));
			}
			writer.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
