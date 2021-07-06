package com.project.wf.notice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author 왕지민
 * 알림함  프로모션에 관련된 클래스
 *
 */
public class FarmManagement_NoticeEvent {

	private ArrayList<FarmManagement_NoticeEventList> list = new ArrayList<FarmManagement_NoticeEventList>();
	private static String path = "dat\\10. Promotion.dat";
	private Scanner scan = new Scanner(System.in);

	/**
	 * 프로모션 목록을 보여주는 메소드
	 */
	public void eventShowList() {
		System.out.println("=========================");
		System.out.println("프로모션 목록");
		System.out.println("=========================");
		System.out.println("[프로모션 번호]\t[프로모션 제목]\t[프로모션 종류]");

		for (int i = 0; i < list.size(); i++) {
			System.out.println(String.format("%s\t\t%s\t\t%s", list.get(i).getSeq(), list.get(i).getName(),
					list.get(i).getType()));
		}
		System.out.println("=========================");

	}

	/**
	 * 원하는 프로모션을 자세히 보기 위한 메소드
	 * @param login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위한 매개변수
	 */
	public void eventChoice(String login) {
		
		FarmManagement_NoticeMenu noticeMenu = new FarmManagement_NoticeMenu();
		FarmManagement_NoticeMenu_Admin noticeMenuAdmin = new FarmManagement_NoticeMenu_Admin();
		System.out.println("1.자세히 보기");
		System.out.println("0.뒤로가기");
		System.out.println("=========================");
		System.out.print("번호 입력: ");

		switch (scan.nextLine()) {
		case "1":
			System.out.print("선택할 프로모션 번호: ");
			int num = Integer.parseInt(scan.nextLine());
			System.out.println();
			System.out.println("[자세히 보기]");

			System.out.println(String.format("프로모션 번호: %s", list.get(num - 1).getSeq()));
			System.out.println(String.format("프로모션 이름: %s", list.get(num - 1).getName()));
			System.out.println(String.format("프로모션 기간: %s", list.get(num - 1).getPriod()));
			System.out.println(String.format("내용 %s\n", list.get(num - 1).getContent()));
			System.out.println("=========================");
			System.out.println("0.뒤로가기");
			System.out.print("번호입력: ");
			num = Integer.parseInt(scan.nextLine());
			

			if (login.startsWith("X")) {
				noticeMenu.noticeMenu(login);
			} else if (login.startsWith("Z") || login.startsWith("Y")) {
				noticeMenuAdmin.noticeMenu_Admin(login);
			}

			break;
		case "0":
			noticeMenu.noticeMenu(login);
			break;
		default:
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			System.out.println("=========================");
			eventChoice(login);
		}
	}

	/**
	 * 파일에 저장되어있는 프로모션글을 ArrayList에 객체생성하여 요소들 저장하는 메소드
	 * @return 프로모션글이 저장된 ArrayList를 반환
	 */
	public ArrayList<FarmManagement_NoticeEventList> eventListup() {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));

			String line = "";
			FarmManagement_NoticeEventList eventList;
			String seq = "", name = "", type = "", priod = "", content = "";

			while ((line = reader.readLine()) != null) {
				if (line.substring(0, 2).equals("내용")) {
					String tempContent = "";
					while (!(line = reader.readLine()).equals("==========")) {
						tempContent += line + "\r\n";
					}
					content = tempContent;
					eventList = new FarmManagement_NoticeEventList(seq, name, type, priod, content);
					list.add(eventList);
				} else if (line.substring(0, 7).equals("프로모션 번호")) {
					seq = line.substring(9);
				} else if (line.substring(0, 7).equals("프로모션 이름")) {
					name = line.substring(9);
				} else if (line.substring(0, 7).equals("프로모션 종류")) {
					type = line.substring(9);
				} else if (line.substring(0, 2).equals("기간")) {
					priod = line.substring(4);
				}
			}

			reader.close();
			return list;

		} catch (IOException e) {
			System.out.println(e);
		}
		return null;
	}
}
