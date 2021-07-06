package com.project.wf.notice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * 
 * @author 왕지민
 * 공지사항글을 등록하기 위한 클래스
 *
 */
public class FarmManagement_NoticeBoardRegister {
	
	private ArrayList<FarmManagement_NoticeBoardList> list = new ArrayList<FarmManagement_NoticeBoardList>();
	private static String path = "dat\\11. Noticeboard.dat";
	private Scanner scan = new Scanner(System.in);
	
	/**
	 * 공지사항 글을 자세히보거나 추가 하기위한 매뉴 메소드
	 * @param login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위한 매개변수
	 */
	public void noticeBoardResgisterMenu(String login) {
		FarmManagement_NoticeBoard board = new FarmManagement_NoticeBoard();
		FarmManagement_NoticeMenu_Admin menuAdmin = new FarmManagement_NoticeMenu_Admin();
		
		list = board.boardListup();
		board.boardShowList();
		
		System.out.println("1.자세히 보기");
		System.out.println("2.등록하기");
		System.out.println("0.뒤로가기");
		System.out.println("=========================");
		System.out.print("번호입력: ");
		
		switch(scan.nextLine()) {
		case "1":
			board.boardListup();
			board.boardChoice(login);
			break;
		case "2":
			noticeBoardResgister();
			noticeBoardWrite();
			break;
		case "0":
			menuAdmin.noticeMenu_Admin(login);
			break;
		default:
			System.out.println("[잘못 입력하셨습니다. 다시 입력해주세요.]");
			System.out.println("=========================");
			noticeBoardResgisterMenu(login);
		}
		menuAdmin.noticeMenu_Admin(login);
	}
	
	/**
	 * 추가된 공지사항글을 파일에 저장하는 메소드
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
		System.out.println("[완료됐습니다.]");

	}

	/**
	 * 공지사항 글을 추가하기위해 작성하는 메소드
	 */
	private  void noticeBoardResgister() {
		FarmManagement_NoticeBoardList event;
		String content = "";
		
		System.out.printf("공지사항 번호: %s\n",String.format("G%03d",getSeq()));
		System.out.print("공지사항 이름: ");
		String name = scan.nextLine();
		System.out.println("내용 (exit 입력시 종료)");
		
		while (true) {
			String temp = scan.nextLine();
			if (temp.equals("exit")) {
				break;
			}
			content += temp + "\r\n";
		}
		
		Calendar now = Calendar.getInstance();
		event = new FarmManagement_NoticeBoardList(String.format("G%03d",getSeq()),name, 
				String.format("%tF %tT", now, now), content);
		list.add(event);
	}
	
	/**
	 * 공지사항 글번호를 최근번호로 출력하는 메소드
	 * @return 공지사항 글번호
	 */
	private int getSeq() {

		int max = 0;

		for (FarmManagement_NoticeBoardList m : list) {
			if (Integer.parseInt(m.getSeq().substring(1, 4)) > max) {
				max = Integer.parseInt(m.getSeq().substring(1, 4));
			}
		}

		return (max + 1);
	}
}
