package com.project.wf.notice;

import java.util.Scanner;

/**
 * 
 * @author 왕지민
 * 직원/관리자 알림함 매뉴 클래스
 *
 */
public class FarmManagement_NoticeMenu_Admin {
	private Scanner scan = new Scanner(System.in);
	/**
	 * 직원/관리자 알림함 매뉴 출력 메소드
	 * @param login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위한 매개변수
	 */
	public void noticeMenu_Admin(String login) {

		System.out.println("=========================");
		System.out.println("1.프로모션 등록");
		System.out.println("2.프로모션 삭제");
		System.out.println("3.공지사항 등록");
		System.out.println("4.공지사항 삭제");
		System.out.println("5.텃밭 관련 메시지");
		System.out.println("0.뒤로가기");
		System.out.println("=========================");
		System.out.print("번호 입력: ");

		switch (scan.nextLine()) {
		case "1":
			FarmManagement_NoticeEventRegister eventRegister = new FarmManagement_NoticeEventRegister();
			eventRegister.noticeEventResgisterMenu(login);
			break;
			
		case "2":
			FarmManagement_NoticeEventDelete eventDelete = new FarmManagement_NoticeEventDelete();
			eventDelete.noticeEventDeleteMenu(login);
			break;
			
		case "3":
			FarmManagement_NoticeBoardRegister boardRegister = new FarmManagement_NoticeBoardRegister();
			boardRegister.noticeBoardResgisterMenu(login);
			break;
			
		case "4":
			FarmManagement_NoticeBoardDelete boardDelete = new FarmManagement_NoticeBoardDelete();
			boardDelete.noticeBoardDeleteMenu(login);
			break;
			
		case "5":	//수정필요
			FarmManagement_NoticeMessage message = new FarmManagement_NoticeMessage();
			FarmManagement_NoticeMessageSend_Admin messageSend = new FarmManagement_NoticeMessageSend_Admin();
			message.noticeMessageListup(login);
			message.noticeMessageShowList(login);
			messageSend.messageMenu(login);

			break;

		case "0":
			break;
		default:
			System.out.println("[잘못 입력하셨습니다. 다시 입력해주세요.]");
			System.out.println("=========================");
			noticeMenu_Admin(login);
		}
	}
}
