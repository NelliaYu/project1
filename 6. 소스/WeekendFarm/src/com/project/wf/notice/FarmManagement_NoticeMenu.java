package com.project.wf.notice;

import java.util.Scanner;

/**
 * 
 * @author 왕지민
 * 회원 알림함 매뉴 클래스
 *
 */
public class FarmManagement_NoticeMenu {
	private Scanner scan = new Scanner(System.in);
	/**
	 * 직원/관리자 알림함 매뉴 출력 메소드
	 * @param login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위한 매개변수	 
	 *
	 */
	public void noticeMenu(String login) {

		System.out.println("=========================");
		System.out.println("1.프로모션 목록");
		System.out.println("2.공지사항 목록");
		System.out.println("3.텃밭 관련 메시지");
		System.out.println("0.뒤로가기");
		System.out.println("=========================");
		System.out.print("번호 입력: ");

		switch (scan.nextLine()) {
		case "1":
			FarmManagement_NoticeEvent noticeEvent = new FarmManagement_NoticeEvent();
			noticeEvent.eventListup();
			noticeEvent.eventShowList();
			noticeEvent.eventChoice(login);
			break;
		case "2":
			FarmManagement_NoticeBoard noticeBoard = new FarmManagement_NoticeBoard();
			noticeBoard.boardListup();
			noticeBoard.boardShowList();
			noticeBoard.boardChoice(login);

			break;
		case "3":	//수정필요
			FarmManagement_NoticeMessage message = new FarmManagement_NoticeMessage();
			FarmManagement_NoticeMessageSend messageSend = new FarmManagement_NoticeMessageSend();
			message.noticeMessageListup(login);
			message.noticeMessageShowList(login);
			messageSend.messageMenu(login);

			break;

		case "0":
			break;
		default:
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			System.out.println("=========================");
			noticeMenu(login);
		}
	}

}
