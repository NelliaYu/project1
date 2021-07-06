package com.project.wf.notice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * 
 * @author 왕지민
 * 프로모션글을 등록하기 위한 클래스
 *
 */
public class FarmManagement_NoticeEventRegister {
	
	private ArrayList<FarmManagement_NoticeEventList> list = new ArrayList<FarmManagement_NoticeEventList>();
	private static String path = "dat\\10. Promotion.dat";
	private Scanner scan = new Scanner(System.in);
	
	/**
	 * 프로모션 글을 자세히보거나 추가 하기위한 매뉴 메소드
	 * @param login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위한 매개변수
	 */
	public void noticeEventResgisterMenu(String login) {
		FarmManagement_NoticeEvent event = new FarmManagement_NoticeEvent();
		FarmManagement_NoticeMenu_Admin menuAdmin = new FarmManagement_NoticeMenu_Admin();
		
		list = event.eventListup();
		event.eventShowList();
		
		System.out.println("1.자세히 보기");
		System.out.println("2.등록하기");
		System.out.println("0.뒤로가기");
		System.out.println("=========================");
		System.out.print("번호입력: ");
		
		switch(scan.nextLine()) {
		case "1":
			event.eventListup();
			event.eventChoice(login);
			break;
		case "2":
			noticeEventResgister();
			break;
		case "0":
			menuAdmin.noticeMenu_Admin(login);
			break;
		default:
			System.out.println("[잘못 입력하셨습니다. 다시 입력해주세요.]");
			System.out.println("=========================");
			noticeEventResgisterMenu(login);
		}
		menuAdmin.noticeMenu_Admin(login);
	}
	
	/**
	 * 추가된 프로모션글을 파일에 저장하는 메소드
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
		System.out.println("[완료됐습니다.]");

	}

	/**
	 * 프로모션 글을 추가하기위해 작성하는 메소드
	 */
	private  void noticeEventResgister() {
		FarmManagement_NoticeEventList event;
		String content = "";
		
		System.out.printf("프로모션 번호: %s\n",String.format("P%03d",getSeq()));
		System.out.print("프로모션 이름: ");
		String name = scan.nextLine();
		System.out.print("프로모션 종류: ");
		String type = scan.nextLine();
		System.out.println("내용 (exit 입력시 종료)");
		
		while (true) {
			String temp = scan.nextLine();
			if (temp.equals("exit")) {
				break;
			}
			content += temp + "\r\n";
		}
		
		Calendar now = Calendar.getInstance();
		event = new FarmManagement_NoticeEventList(String.format("P%03d",getSeq()),name, 
				type,String.format("%tF %tT", now, now), content);
		list.add(event);
		eventWrite();
	}
	
	/**
	 * 프로모션 글번호를 최근번호로 출력하는 메소드
	 * @return 프로모션 글번호
	 */
	private int getSeq() {

		int max = 0;

		for (FarmManagement_NoticeEventList m : list) {
			if (Integer.parseInt(m.getSeq().substring(1, 4)) > max) {
				max = Integer.parseInt(m.getSeq().substring(1, 4));
			}
		}

		return (max + 1);
	}
}
